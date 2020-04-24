import java.util.Arrays;
import java.util.*;
import java.io.*;
import java.math.*;
// Hadiza Mamman



public class BookRecommendations {
	
	

	public static void main(String [] args) throws FileNotFoundException{
		
		
		double [][] ratings= new double[30][20];
		
		double num = 0;
		
		double [] user = new double[20];
		
		String [] bookArr = new String[20];
		
		double  sum =0;
	
		Scanner scan = new Scanner(System.in);
		
	   
	    Scanner readBooks = new Scanner(new File("books.txt"));
	    
	    for(int i =0; i<20; i++) {
		 bookArr[i] = readBooks.nextLine() ;
	    }
	    
	
	    readBooks.close();
	    
	for( int i =0; i<20; i++) {
		
			System.out.println("Enter a rating between 1 an 5 or -1, if you haven't read the book");
			System.out.print(bookArr[i]);
			 user[i ]= scan.nextDouble();
			 
	}
	
			 
	Scanner input2 = new Scanner(new File("ratings.txt"));
	for(int i=0;i<30;i++) {
				 for(int j=0;j<20;j++) {
					 ratings[i][j] = input2.nextInt();
					 
				 }
			 }
			 
	 input2.close();	
	
	calcSqr(ratings,0);
	calcBoth(user,ratings,0);
	similarity(ratings,user);
	avg(ratings,user);
	indexOfLargest(ratings,user);
	
	
	System.out.println(bookArr[indexOfLargest(ratings,user)]);
	
	}
	public static double [] avg(double ratings[][],double user[]) {
		double [] arrayScore = similarity(ratings, user);
		double[] weightedScores = new double[20];
		
		
		//loop through weightedScores
		for(int h =0; h<20; h++) {
			double sum = 0.0;
			int count = 0;
		for(int i=0; i<30 ; i++) {
			sum += ratings[i][h]*arrayScore[h];
		}
		weightedScores[h]= sum/count;
		
		}
		return weightedScores ;
	
	}
	
		public static double calcSqr(double arr[][], int p) {
			double sum = 0;
			double [] user = new double[20];
			double [][] ratings= new double[30][20];
			for(int i=0; i<20; i++) {
				if (user[i]!=-1 && ratings[p][i]!=-1)
            {
				sum += (arr[p][i]* arr[p][i]);
			}
			
		}
			return Math.sqrt(sum);
		}
		
		
		
		public static double calcBoth(double user[],double ratings[][],int p) {
			double sum = 0;
			
			for(int i =0; i<20; i++) {
				if (user[i]!=-1 && ratings[p][i]!=-1)
				{
					sum+=(user[i]*ratings[p][i]);
				}
			
			}
			return sum;
		}

		public static double [] similarity(double ratings[][], double user[]) {
			double p1 = 0;
			double p2 = 0;
			double both = 0;
			double similarity = 0;
			double [] simScore = new double[30];
			
			
			p2 = calcSqr(ratings, 0);
			for(int i =0; i<30; i++) {
				p1= calcSqr(ratings, i);
				both = calcBoth(user, ratings, i);
				similarity = both/(p1*p2);
				simScore[i] = similarity;
				
			
			}
			return simScore;
		}
		
		public static int indexOfLargest(double ratings[][], double user[]) {
			double [] finale = new double [20];
	    	finale = avg(ratings,user);
	    	int max = 0;
	    	for(int i =0; i<20; i++) {
	    		if(finale[i]> finale[max]) {
	    			max = i;
	    	      
	    		}
	   
	    }
	    	return max;
	    }
}

