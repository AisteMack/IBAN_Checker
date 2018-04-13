package checker;

import java.math.BigInteger;

public class Checker {

	private Countries countries;
	
	
	public Checker() {
		this.countries = new Countries();
	}
	
	public boolean checkIban(Iban number) {
		return cheackIbanNumberLength(number) && cheackIbanChecksum(number);
	}
	
	
	private boolean cheackIbanNumberLength(Iban number) {
		String country = number.getCountry();
		//Check that the  country exists. If not, the IBAN is invalid
		if(countries.hashMapcontainsContry(country)) {
			int length = countries.getLenghtFromHashMap(country);
			//Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid
			if(number.getIban().length() == length) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}

	private boolean cheackIbanChecksum(Iban number) {
		if(ibanStringToInt(number)) {
			//compute the remainder of that number on division by 97
			//if the remainder is 1, the check IBAN test is passed
			return number.getIntegerIban().remainder(BigInteger.valueOf(97))
					.equals(BigInteger.valueOf(1));
		}
		return false;
	}
	
	private boolean ibanStringToInt(Iban number) {
		// A == 65
	    int offset = 55;

		String iban = number.getIban();
		StringBuilder sb = new StringBuilder();
        sb.append(iban.substring(4));
        //Move the four initial characters to the end of the string
        sb.append(iban.substring(0, 4));
        
        //Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35
        for (int i = 0; i < sb.length(); i++) {
            Character c = sb.charAt(i);
            if (!Character.isDigit((int) c)) {
                if (Character.isLetter((int) c)) {
                    sb.replace(i, i + 1, String.valueOf((int) c - offset));
                } else {
                    // invalid IBAN code, contains something other than digits and letters
                    return false;
                }
            }
        }
        //Interpret the string as a decimal integer
        number.setIntegerIban(new BigInteger(sb.toString()));
        return true;
	}
	
}
