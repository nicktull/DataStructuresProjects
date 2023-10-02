package Project3;
import java.util.Scanner;

public class Project3 {
	 static DLList<PolyList> polyList;
	
	 
	public static void main(String[] args) {
		polyList = new DLList<PolyList>();
		new PolyList();
		new Project3();
	}	
	
	
	public Project3(){
		runProject3();
	}	
	
		// This method scans the input from the user to identify the operator.
		private void runProject3() {
			Scanner sc = new Scanner(System.in);
			String inputLine = sc.nextLine();
			if(inputLine.equals("QUIT")) {     // If the user inputs QUIT, the list will be cleared and the program will exit. 
				PolyList.clear();
			}else {
				int index = inputLine.indexOf(' ');
				String operator = inputLine.substring(0, index);    //Separates the operator and the coefficients 
				String polyData = inputLine.substring(index + 1);    
				
				//If not not null then the program will execute one of the if/else if statements below
				if(operator!=null) {
					if(operator.equals("INSERT")) {
						PolyList.insert(polyData);
						
					}else if(operator.equals("DELETE")) {
						PolyList.delete(polyData);
						
					}else if(operator.equals("SEARCH")) {
						PolyList.search(polyData);
						
					}else if(operator.equals("UPDATE")) {
						PolyList.delete(polyData);
						PolyList.insert(polyData);
					}
				}       
			}
			sc.close();   //Closes the scanner after complete.
	}
	
}
