package org.datum.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexPojo {
	private static final int extraField = 0;
	
	private String name;
	private int age;
	private Date date;
}
