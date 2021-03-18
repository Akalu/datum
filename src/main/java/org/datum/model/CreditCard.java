package org.datum.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard implements Serializable {
	private static final long serialVersionUID = -8007354727854375798L;

	private CardType type;
	private String number;
	private String securityCode;
	private String expdate;
}
