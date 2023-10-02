package Project3;

public class Term {
	private int coef;
	private int	x;
	private int y;
	private int z;    //Constructor of the 4 numbers that deal with each term
	
	
	public Term(int coef, int x, int y, int z) {
		this.coef = coef;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Establishing getters and setters for int coef and the degrees for int x, int y, int z.
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	
	//Overriding the toString() method 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//Using a string builder to build a the appropriate polynomial format
		// If the coefficient is 0 then the polynomial will also be 0
		if(coef == 0) {
			return "0";
		}
		//Determining if the coef is 1 and whether or not the coef is negative.
		if(coef != 1 && coef != -1) {
			if(coef < -1) {
				coef = coef*-1;
				sb.append(coef);
			}else {
				sb.append(coef);
			}
		}else if(coef == -1) {
			sb.append("-");
		}
		//Determining if the degree is 0, 1, or greater than 1 and then appending to string builder for x, y, and z degrees.
		if(x > 0) {
			sb.append("(x");
			if(x > 1) {
				sb.append("^" + x + ")");
			}else if(x==1) {
				sb.append(")");
			}
		}
		if(y > 0) {
			sb.append("(y");
			if(y > 1) {
				sb.append("^" + y + ")");
			}else if(y==1) {
				sb.append(")");
			}
		}
		if(z > 0) {
			sb.append("(z");
			if(z > 1) {
				sb.append("^" + z + ")");
			}else if(z==1) {
				sb.append(")");
			}
		}
		//If coefficient is 1 and all degrees for x, y, and z are all 0 then return the string "1".
		if(sb.length()==0) {
			return "1";
		}
		return sb.toString();
	}
}
