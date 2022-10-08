package com.AOM.aomentityobject;

import java.io.IOException;
import java.util.Set;

public class AOMUtils {
	
	public static void printEntity(ProductEntity entity) {
		ProductEntityType type = entity.getType();
		Set<String> propNames = type.getPropertyName();
		System.out.println("TYPE "+type.getName());
		for(String propNmae : propNames) {
			System.out.println(propNmae+": " + entity.getProperty(propNmae));
		}
	}
}
