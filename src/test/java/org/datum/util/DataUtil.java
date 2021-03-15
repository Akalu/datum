package org.datum.util;

import org.datum.datasource.model.DataSchema;

public class DataUtil {
	
	public static DataSchema getSampleSchemaLoc() {
		
		DataSchema schema = new DataSchema(3);
		schema.addType("state", "java.lang.String", 0);
		schema.addType("city", "java.lang.String", 1);
		schema.addType("zip_code", "java.lang.String", 2);
		return schema;

	}
	
	public static DataSchema getSampleSchemaPerson() {
		
		DataSchema schema = new DataSchema(3);
		schema.addType("gender", "java.lang.String", 0);
		schema.addType("first_name", "java.lang.String", 1);
		return schema;

	}
	


}
