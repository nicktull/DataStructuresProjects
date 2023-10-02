package Project3;

public class Polynomial{
	String name;               //Create new terms and add to terms list
	private DLList<Term> terms;
	
	//Establishing getters and setters for the name and terms of the polynomials.
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	
	//Breaking up the terms of the polynomial and then inserting them into the terms list. 
	public Polynomial(String name, String inputLine) {             
		terms = new DLList<Term>();
		this.name = name;
		this.terms = new DLList<>();
		String spaceRemover = inputLine.substring(1);
		int indexStart = 0;
		int indexEnd = spaceRemover.indexOf(' ');
		while(indexEnd != -1) {
			String[] values = spaceRemover.substring(indexStart, indexEnd).split(",");
			int values0= Integer.parseInt(values[0]);  
			int values1= Integer.parseInt(values[1]);  
			int values2= Integer.parseInt(values[2]);
			int values3= Integer.parseInt(values[3]);
			terms.insertLast(new Term(values0, values1, values2, values3));
			indexStart = indexEnd + 1;
			indexEnd = spaceRemover.indexOf(' ', indexStart);
		}
	}
	
	//Overriding the toString() 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean firstTerm = true;
		int n = 0;
		terms.first();              //Moving the current node to the first node in the terms list. 
		while (n < terms.size()) {  //Loops through the terms list and appends the terms to the StringBuilder.
			Term term = terms.dataRead();
			if(firstTerm) {
				sb.append(term.toString());
				firstTerm = false;
			}else {
				if(term.getCoef() < 0) {
					sb.append(" - " + term.toString().substring(0)); 
				}else {
					sb.append(" + " + term.toString().substring(0));
				}
			}
			n++;
			terms.next();          //Moving to the next term in the list
		}
		return name + " = " + sb.toString();  //Return the name and the polynomial in the appropriate format.
	}
}
// Break down coefficient groups in this class