package org.datum.example.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePojo implements Serializable {
	private static final long serialVersionUID = 101L;

	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private String address;
	
}
