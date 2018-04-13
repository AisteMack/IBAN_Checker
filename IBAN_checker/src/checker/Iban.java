package checker;

import java.math.BigInteger;

public class Iban {
	private String Iban;
	private String country;
	private BigInteger integerIban;
	
	public String getCountry() {
		return country;
	}

	public String getIban() {
		return Iban;
	}
	
	public Iban(String fullIban) {
		this.Iban = fullIban;
		if(this.Iban.length() >= 2)
		this.country = this.Iban.substring(0, 2);
	}
	
	public BigInteger getIntegerIban() {
		return integerIban;
	}
	
	public void setIntegerIban(BigInteger integerIban) {
		this.integerIban = integerIban;
	}
	
	
}
