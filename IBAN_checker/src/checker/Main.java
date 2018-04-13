package checker;



import ui.IbamCheckerUI;

public class Main {
	
	public static void main(String[] args) {
		Checker ck = new Checker();
		IbamCheckerUI ui = new IbamCheckerUI(ck);
		ui.run();
		  
	}
	
}
