package org.datum.example.pojo;

import java.io.Serializable;

import org.datum.model.GeoLocation;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private GeoLocation location;
	
	@Override
	public String toString() {
		Gson g = new Gson();
		return g.toJson(this);
	}
	
}
