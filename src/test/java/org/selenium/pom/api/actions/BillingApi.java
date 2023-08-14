package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.constants.LocationCodes;
import org.selenium.pom.objects.BillingAddress;

public class BillingApi {
	
	private Cookies cookies;
	
	public BillingApi(Cookies cookies) {
		this.cookies = cookies;
	}
	
	public Response addBillingAddress(BillingAddress billingAddress) {
		Header header = new Header("Content-Type","application/x-www-form-urlencoded");
		Headers headers = new Headers(header);
		HashMap<String, Object> formParams = new HashMap<>();
		formParams.put("billing_first_name", billingAddress.getFirstName());
		formParams.put("billing_last_name", billingAddress.getLastName());
		formParams.put("billing_company", billingAddress.getCompany());
		formParams.put("billing_country", LocationCodes.getCountryCode(billingAddress.getCountry()));
		formParams.put("billing_address_1", billingAddress.getAddressLineOne());
		formParams.put("billing_city", billingAddress.getCity());
		formParams.put("billing_state", LocationCodes.getStateCode(billingAddress.getState()));
		formParams.put("billing_postcode", billingAddress.getPostCode());
		formParams.put("billing_phone", billingAddress.getPhone());
		formParams.put("billing_email", billingAddress.getEmail());
		formParams.put("save_address", "Save address");
		formParams.put("woocommerce-edit-address-nonce", fetchBillingNonceValueUsingJsoup());
		formParams.put("action", "edit_address");
		
		Response response = ApiRequest.post(
                EndPoint.ACCOUNT_EDIT_BILLING_ADDRESS.url, headers, formParams, cookies);
		if(response.getStatusCode() != 302) {
			throw new RuntimeException("Failed to update the billing address, HTTP Status Code: " + response.getStatusCode());
		}
		this.cookies = response.getDetailedCookies();
		return response;
	}


	public Response getAccountBilling() {
		Response response = ApiRequest.get(EndPoint.ACCOUNT_EDIT_BILLING_ADDRESS.url, cookies);
		if(response.getStatusCode() != 200) {
			throw new RuntimeException("Failed to fetch the billing address, HTTP Status Code: " + fetchBillingNonceValueUsingJsoup() + response.getStatusCode());
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
