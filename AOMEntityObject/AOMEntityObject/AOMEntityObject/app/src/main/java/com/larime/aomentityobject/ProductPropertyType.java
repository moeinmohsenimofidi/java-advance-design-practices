package com.AOM.aomentityobject;

public class ProductPropertyType {
	
	private String name;
	private Class<?> type;
	
	public ProductPropertyType(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}


}
