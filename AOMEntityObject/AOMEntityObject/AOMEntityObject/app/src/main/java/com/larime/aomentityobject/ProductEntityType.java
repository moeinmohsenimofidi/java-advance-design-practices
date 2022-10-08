package com.AOM.aomentityobject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductEntityType {
	
	private String name;
	private Map<String, ProductPropertyType> propertyTypes;
	
	public ProductEntityType(String name) {
		this.name = name;
		propertyTypes = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public ProductPropertyType getPropertyType(String propertyName) {
		return propertyTypes.get(propertyName);
	}
	
	public Set<String> getPropertyName(){
		return propertyTypes.keySet();
	}
	
	public boolean existsProprty(String name) {
		return propertyTypes.containsKey(name);
	}
	
	public void addPropertyType(ProductPropertyType propType) {
		propertyTypes.put(propType.getName() , propType);
	}
	
	public ProductEntity newEntity() {
		return new ProductEntity(this);
	}
	
	
	
}
