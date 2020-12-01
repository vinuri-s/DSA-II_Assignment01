import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

class Hash{
	
	private String[] prices ;
	public Hash(int c) 
	 {
		 prices = new String[c];
	 }

	private int hashNumber(String price,int size) 
    {
		int key =  Integer.parseInt(price);
                return key % size;
    }
	
	 public void input(String price,int m) 
    {
		int key = hashNumber(price,m);
		int k = key;
		int h = 1;
		
		do{
			if(prices[k] == null){
				prices[k] = price;
				break;
			}
			
			k = (k + 1) % m;
		}while(k != key);
		
    }
	
	
	
	public void sum(int price,int m)
    {
		int c=0;
		int n2;
		
		for (int i = 0; i < m; i++) {
			n2 = price - Integer.parseInt(prices[i]);
			
			if(n2 <= 0){
				
				continue;
			}
			String n2String = Integer.toString(n2);
			
			int n2Hash = hashNumber(n2String,m);
			
			int remInt = Integer.parseInt(prices[n2Hash]);
		
			
			if(remInt == n2){
				c = c+1;
				break;
			}
		}
		if(c==1){
			System.out.println("1");
		}else{
			System.out.println("0");
		}
			
    } 		
}
public class PriceList{

		public static void main(String[] args) throws FileNotFoundException {
			
			int c=0;

			File file = new File("PriceList.txt");
			
			
			Scanner sizeScan = new Scanner(file);
		
			while(sizeScan.nextLine() != null)
			{
				c=c+1;
				if(!sizeScan.hasNextLine()){
					break;
				}
			}
			Hash hashPrice = new Hash(c);
			Scanner inputs = new Scanner(file);
			while(inputs.hasNextLine())
			{
				hashPrice.input(inputs.next(),c);
			}

			System.out.print("Input:");
			Scanner scan = new Scanner(System.in);

			hashPrice.sum(scan.nextInt(),c );
		


	}
}