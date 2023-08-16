package edu.java.lab1;
/**
 * @author Bryzgalova Ekaterina 
 * @version 1.1 
 */
public class First_lab {/** * This is my Main class, here I will write my code */ 
	/** @param arr * Static array  */ 
	static int arr[]= {4, 6, 2, 8, 9, 123, 4, 5, 6, 7, 3,1};
	
	/**
	 * The application's entry point
	 * @param args an array of command-line arguments for the application
	 */
	public static void main(String[] args) { 
		int temp=0;
		for (int i = 0; i < arr.length - 1; i++){
			for (int j = 0; j < arr.length - i - 1; j++) { /** *Sorting bubble*/ 
				boolean check = arr[j] > arr[j + 1]; 
				if (check) { /** *Checking the need to exchange variable values*/ 
					 temp = arr[j];
					arr[j] = arr[j + 1]; 
					arr[j + 1] = temp; 
					
					} 
				} 
			} 
		for(int i = 0; i < arr.length; i++){/** *Loop for outputting the result*/
			System.out.print(arr[i]);
			 System.out.print(" ");
			}
		} 
	}