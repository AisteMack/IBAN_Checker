package checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.io.FileReader;

public class IbanFile {
	
	private ArrayList<Iban> ibanList;
	private Checker checker;
	
	
	public IbanFile(String path, String name, Checker checker) throws IOException {
		super();
		this.ibanList = getListOfIbanFromFile(path, name);
		this.checker = checker;
	}

	private ArrayList<Iban> getListOfIbanFromFile(String path, String fileName) throws IOException {
		ArrayList<Iban> ibanList = new ArrayList<>();
		String filePath = path+"\\"+fileName;
		File file = new File(filePath);
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String iban;
		while ((iban = br.readLine()) != null) {
			ibanList.add(new Iban(iban));
		}
		br.close();

		return ibanList;
	}
	
	public boolean checkIbans(String path, String name) throws FileNotFoundException, UnsupportedEncodingException {
		String[] partsOfName = name.split("\\.");
		String outFile = path + "\\" + partsOfName[0] + ".out";
		
		PrintWriter writer = new PrintWriter(outFile, "UTF-8");
		for(Iban temp : ibanList) {
			
			writer.println(temp.getIban() + ";" +checker.checkIban(temp));
		}
		writer.close();
		return true;
	}
}
