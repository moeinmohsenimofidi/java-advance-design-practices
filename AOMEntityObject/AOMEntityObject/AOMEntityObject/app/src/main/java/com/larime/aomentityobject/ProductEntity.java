package com.AOM.aomentityobject;

import java.util.HashMap;
import java.util.Map;

public class ProductEntity {
	
	private ProductEntityType type;
	private Map<String, ProductProperty> propValues;

	protected ProductEntity(ProductEntityType type) {
		this.type = type;
		this.propValues = new HashMap<>();
	}
	
	public Object getProperty(String propName) {
		return propValues.get(propName).getValue();
	}
	
	public void setProperty(String propName , Object value) {
		if(!type.existsProprty(propName)) {
			throw new RuntimeException("Property "+ propName + " does not exist in " + type.getName());
		}
		ProductPropertyType propType = type.getPropertyType(propName);
		ProductProperty propValue = new ProductProperty(propType);
		propValue.setVlue(value);
		propValues.put(propName, propValue);
		
	}

	public ProductEntityType getType() {
		return type;
	}
	
	
}
