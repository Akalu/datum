package org.datum.example.pojo;

import java.io.Serializable;
import java.util.UUID;

import org.datum.model.CreditCard;
import org.datum.model.Gender;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePojo implements Serializable {
	private static final long serialVersionUID = 101L;

	private String uid = UUID.randomUUID().toString();
	private String firstName;
	private String lastName;
	private Gender gender;
	private int age;
	private String email;
	private CreditCard creditCard;
	
	@Override
	public String toString() {
		Gson g = new Gson();
		return g.toJson(this);
	}
	
}
