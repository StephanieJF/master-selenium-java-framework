package org.selenium.pom.objects;

import java.io.IOException;

import org.selenium.pom.utils.JacksonUtils;

public class Product {
	
	private int id;
	private String name;
	private boolean featured;
	private boolean onStorePage;
	
	public Product() {
	}
	
	public Product(int id) throws IOException {
		Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
		for (Product product : products) {
			if(product.getId() == id) {
				this.id = id;
				this.name = product.getName();
				this.featured = product.getIsFeatured();
				this.onStorePage = product.getOnStorePage();
			}		
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	public boolean getOnStorePage() {
		return onStorePage;
	}

	public void setOnStorePage(boolean onStorePage) {
		this.onStorePage = onStorePage;
	}

}
