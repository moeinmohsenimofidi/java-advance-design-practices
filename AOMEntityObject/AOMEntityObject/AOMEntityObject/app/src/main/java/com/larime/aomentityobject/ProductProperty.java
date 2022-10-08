package com.AOM.aomentityobject;

public class ProductProperty {
	
	private ProductPropertyType propType;
	private Object value;
	
	public ProductProperty(ProductPropertyType propType) {
		this.propType = propType;
		value = null;
	}
	
	public ProductPropertyType getPropType() {
		return propType;
	}

	public Object getValue() {
		return value;
	}

	public void setVlue(Object value){
		if(!propType.getType().isInstance(value)) {
			throw new RuntimeException("the value " + value.toString() +
				" does have the type "+ propType.getType().getName());
		}
		this.value = value;
	}
}
