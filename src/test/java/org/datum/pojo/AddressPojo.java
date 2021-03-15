package org.datum.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressPojo implements Serializable {
	private static final long serialVersionUID = 100L;

	private String address;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	
}
