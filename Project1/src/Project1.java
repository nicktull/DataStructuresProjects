import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Project1 {
	static int numOfMatrices;                   // Represents the number of matrices from the System.in
    static char [][][] imageHolder;
    
	private static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
        if(input!=null) numOfMatrices = Integer.parseInt(input);
        char[][] array;                         
        imageHolder = new char[numOfMatrices][][];
         for(int i=0;i<numOfMatrices;i++){       // This for loop goes through the number of matrices that are given. 
             int row=0,col=0;
             input = sc.nextLine();
             
            while(!input.isEmpty()) {
                row = Integer.parseInt(input.substring(0,1));   // Gets the row number 
                col = Integer.parseInt(input.substring(2));     // Get the column number
                array = new char[row][col];
                for(int a=0;a<row;a++){                         // These for loops add elements into the 2D array.
                    String line = sc.nextLine();  
                    int d =0;
                    for(int b=0;b<col;b++){
                        char c = line.charAt(d);   // Gets the char at index d from the String 'line'
                            array[a][b]=c;
                            d=d+1;                 // The variable 'd' is incremeted by one to go to the next char in the string 'line'.
                    }
                }
                input = sc.nextLine();
                imageHolder[i] = new char[array.length][array[0].length];
                for(int s=0;s<array.length;s++){      // This for loop adds the 2D arrays to my 3D array called 'imageHolder' to hold the images. 
                    for(int t=0;t<array[0].length;t++){
                        imageHolder[i][s][t]=array[s][t];
                    }
                }
            }                                  
                  
        }
        sc.close();                 // Closes the scanner once 2D arrays have been placed in the 3D array called imageHolder.
        
        int index = numOfMatrices;  // This is the index of the 3D array imageHolder.
        char color = 'a';           // This will be the first color for the first group.

        for(int k=0; k < index; k++){    
            color = 'a';
            HashMap <Integer, Integer> groupCounter = new HashMap<>(); 
           
            for(int row = 0; row < imageHolder[k].length; row++){           
                for(int col = 0; col < imageHolder[k][0].length; col++){       
                    if(imageHolder[k][row][col] == '*'){   
                        int starCount = groupSolver(imageHolder, k, row, col, color);     // gets the number of stars and stores it
                        if(groupCounter.containsKey(starCount)) {                         // if the hashmap is not null
                            groupCounter.put(starCount, groupCounter.get(starCount)+1);   // it appends the count by 1
                        } else {                                                          // if the hashmap is null
                            groupCounter.put(starCount, 1);                               // it assigns the starCount a value of 1
                        }
                        color++;                                        // Increments the color for the next group that is found
                    }
                }
            }
            for(int row = 0; row < imageHolder[k].length; row++){
                for(int col = 0; col < imageHolder[k][0].length; col++){   // Prints the results to System.out
                    System.out.print(imageHolder[k][row][col]);
                }System.out.println();
            }
            SortedSet<Integer> componentSizes = new TreeSet<>(groupCounter.keySet());
            for(Integer size : componentSizes){                                //Prints the tabulation of the size and count of the matrix
                System.out.println(size + " " + groupCounter.get(size));
            }System.out.println();
        }        
    }

    private static int groupSolver(char[][][] imageHolder, int k, int row, int col, char color){  // Recursive solution to looking for groups 
        if(row<0 || row >=imageHolder[k].length || col<0 || col >= imageHolder[k][0].length || imageHolder[k][row][col] != '*') {
            return 0;  // Base case 1
        }
        imageHolder[k][row][col] = color;                   // Sets color for that row and column aka Base Case 2
        return 1 + groupSolver(imageHolder, k, row+1, col, color)
        + groupSolver(imageHolder, k, row-1, col, color)    //These lines identifies asterisks that are around thes point found.
        + groupSolver(imageHolder, k, row, col+1, color)    
        + groupSolver(imageHolder, k, row, col-1, color);                     
    }
}

