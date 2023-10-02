package Project3;

public class PolyList{
	 static DLList<Polynomial> polynomials;   //Creating new polynomials and adding to polyList
	 										  //separate name and the coefficients
	 public PolyList() {
		 polynomials = new DLList<Polynomial>();   
	 }
	 
	 
	 public static void insert(String polyData) {
		 int index = polyData.indexOf(' ');
		 String polyDataName = polyData.substring(0, index);
		 int n = 0;
		 if(polynomials.size()>0) {       
			 polynomials.first();
			 while(n < polynomials.size()) {
				 if(polyDataName.equals(polynomials.dataRead().getName())){
					 System.out.println("POLYNOMIAL " + polyDataName + " ALREADY INSERTED");
					 return;
				 }
				 n++;
				 polynomials.next();
			 }	 
			polynomials.insertLast(new Polynomial(polyData.substring(0,1), polyData.substring(1) + " "));
			System.out.println(polynomials.dataRead());
		 }else {
			 polynomials.insertFirst(new Polynomial(polyData.substring(0,1), polyData.substring(1) + " "));
			 System.out.println(polynomials.dataRead());
	
		 }
		 
	 }
	 
	 
	 public static void delete(String polyData) {
		 int index = polyData.indexOf(' ');
		 String polyDataName = polyData.substring(0, index);
		 int n = 0;
		 polynomials.first();
		 while(n < polynomials.size()) {
			 polynomials.next();
			 if(polyDataName.equals(polynomials.dataRead().getName())){
				 System.out.println("POLYNOMIAL " + polynomials.dataRead().getName() + " SUCCESSFULLY DELETED");
				 polynomials.deleteAtCurrent();
				 return;
				
			 }
			 polynomials.next();
			 n++; 
		 }
		 System.out.println("POLYNOMIAL " + polyDataName + " DOES NOT EXIST");
	 }
	 
	 
	 public static void search(String polyData) {
		 int index = polyData.indexOf(' ');
		 String polyDataName = polyData.substring(0, index);
		 int n = 0;
		 polynomials.first();
		 while(n < polynomials.size()) {
			 if(polyDataName.equals(polynomials.dataRead().getName())){
				 System.out.println(polynomials.dataRead());
			 }
			 polynomials.next();
			 n++;
		 }
		 System.out.println("POLYNOMIAL " + polyDataName + " DOES NOT EXIST");
	 }
	 
	 
	 public static void clear() {
		 polynomials.clear();
		 System.exit(0);
	 }
}
