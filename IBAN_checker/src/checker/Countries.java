package checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Countries {
	private HashMap<String, Integer> countriesMap = new HashMap<String, Integer>();
	
	
	
	public Countries() {
		readCountries();
	}

	private void readCountries() {
		
		InputStream is = Countries.class.getResourceAsStream("countries");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
		String st;
		try {
			while ((st = br1.readLine()) != null) {
			//File format: Country/Codes/Length
				String[] parts = st.split("/");
				countriesMap.put(parts[1].toUpperCase(), Integer.parseInt(parts[2]));
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
			 
	}
	
	public int getLenghtFromHashMap(String key) {
		return countriesMap.get(key);
	}
	
	public boolean hashMapcontainsContry(String key) {
		return countriesMap.containsKey(key);
	}
	
	public void printCountriesMap() {
		for (String name: countriesMap.keySet()){

            String key =name.toString();
            String value = countriesMap.get(name).toString();  
            System.out.println(key + " " + value);  


		} 	
	}
}
