package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.constants.LocationCodes;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.utils.ConfigLoader;

public class BillingApi {
	
	private Cookies cookies;
	
	public BillingApi(Cookies cookies) {
		this.cookies = cookies;
	}
	
	public Response addBillingAddress(BillingAddress billingAddress) {
		Header header = new Header("content-type", "application/x-www-form-urlencoded");
		Headers headers = new Headers(header);
		HashMap<String, Object> formParams = new HashMap<>();
		formParams.put("billing_first_name", billingAddress.getFirstName());
		formParams.put("billing_last_name", billingAddress.getLastName());
		formParams.put("billing_country", LocationCodes.getCountryCode(billingAddress.getCountry()));
		formParams.put("billing_address_1", billingAddress.getAddressLineOne());
		formParams.put("billing_city", billingAddress.getCity());
		formParams.put("billing_state", LocationCodes.getStateCode(billingAddress.getState()));
		formParams.put("billing_postcode", billingAddress.getPostCode());
		formParams.put("billing_company", billingAddress.getCompany());
		formParams.put("billing_phone", billingAddress.getPhone());
		formParams.put("woocommerce-edit-address-nonce", fetchBillingNonceValueUsingJsoup());
		formParams.put("action", "edit_address");
		formParams.put("save_address", "Save address");
		formParams.put("billing_email", billingAddress.getEmail());
		
		Response response = 
				given().
					baseUri(ConfigLoader.getInstance().getBaseUrl()).
					headers(headers).
					formParams(formParams).
					cookies(cookies).
					log().all().
				when().
					post("/account/edit-address/billing/").
				then().
					log().all().
					extract().
					response();
		if(response.getStatusCode() != 302) {
			throw new RuntimeException("Failed to update the billing address, HTTP Status Code: " + response.getStatusCode());
		}
		this.cookies = response.getDetailedCookies();
		return response;
	}


	public Response getAccountBilling() {
		Response response =
		given().
			baseUri(ConfigLoader.getInstance().getBaseUrl()).
			cookies(cookies).
			log().all().
		when().
			get("/account/edit-address/billing/").
		then().
			log().all().
			extract().
			response();
		if(response.getStatusCode() != 200) {
			throw new RuntimeException("Failed to fetch the billing address, HTTP Status Code: " + response.getStatusCode());
		}
		return response;
	}
	
	public String fetchBillingNonceValueUsingJsoup() {
		Response response = getAccountBilling();
		Document doc = Jsoup.parse(response.body().prettyPrint());
		Element element = doc.selectFirst("#woocommerce-edit-address-nonce");
		assert element !=null;
		return element.attr("value");
	}
}
