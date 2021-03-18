package org.datum.example.pojo;

import java.io.Serializable;
import java.util.UUID;

import org.datum.model.CreditCard;
import org.datum.model.Gender;

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

	private String uid = UUID.randomUUID().toString();
	private String firstName;
	private String lastName;
	private Gender gender;
	private int age;
	private String email;
	private CreditCard creditCard;
	
}
