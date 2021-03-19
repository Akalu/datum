package org.datum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class Country {
	private String name;
	private Region region;
	private SubRegion subRegion;
	private String countryCode;
	private int phoneCode;
}
