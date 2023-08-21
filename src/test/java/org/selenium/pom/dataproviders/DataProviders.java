package org.selenium.pom.dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "getFeaturedProducts", parallel=false)
	public Object[] getFeaturedProducts() throws IOException {
	 Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
	 List<Product> featuredProductsArrayList = new ArrayList<>();
	 	for (Product product : products) {
			if(product.getIsFeatured()) {
				featuredProductsArrayList.add(product);
			}
		}
	 	return featuredProductsArrayList.toArray();
	}
	
	
	@DataProvider(name = "getStorePageProducts", parallel=true)
	public Object[] getStorePageProducts() throws IOException {
		Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
		List<Product> storePageProductsArrayList = new ArrayList<>();
		for(Product product : products) {
			if(product.getOnStorePage()) {
				storePageProductsArrayList.add(product);
			}
		}
		return storePageProductsArrayList.toArray();
	}
	
	@DataProvider(name = "getBillingAddresses", parallel=true)
	public Object[] getBillingAddresses() throws IOException {
		return JacksonUtils.deserializeJson("billingAddress.json", BillingAddress[].class);
	}
	
}
