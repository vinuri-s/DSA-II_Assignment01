/*provides a set of input streams and a set of output streams used to read and write data to files or other input and output sources*/
import java.io.*;
import java.util.Scanner;
//importing the LinkedList class
import java.util.LinkedList;

@SuppressWarnings("unchecked")//instruct the compiler to ignore or suppress, specified compiler warning in annotated element and all program elements inside that elements

/*This class includes the methods to create an empty hashtable, putting the content in txt file to hashtable, calculate hash value and search the user given relevent value in hash table.... */
class HashTable {
	
        private int pos=0;
        int size = 10;
        LinkedList[] linkhash = new LinkedList[size];

		//Creates hash table - Creates a linkedlist of LinkedLists(known as Array of LL)
		 public void hashTable(){
            for (int i=0; i<size ; i++){
                linkhash[i] = new LinkedList();//construct empty linked list 

            }
        }
		
		/* Calculating a String's(key=name) hash value via polynomial accumulation and Horner's rule.*/
        public int computeHash(String key, int val){
			
		int hash = 0;
        
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        
        hash %= size;
		
        if (hash < 0) {
            hash += size;
        }

            this.pos = hash;
            put(key,val);
            return hash;
        }

       /*putting to hash table*/
        public void put(String owner, int pno){
            for (int i=0; i<size ; i++){
                if(i==pos){
                    if (linkhash[pos].isEmpty()){
                        linkhash[i].addFirst(owner);//Adding at the beginning
                        linkhash[i].add(pno);//Add at the end of the LinkedList

                        }else{
                        pos++;
                          put(owner, pno);
                            break;
                        }
                }

            }

        }

		/*search for the owner of the number*/
        public void search(int num){
		int flag=0;
            for (int i=0; i<size ; i++){
               if (linkhash[i].contains(num)){
				   System.out.println(linkhash[i].getFirst());
                  flag=1;
				  
               }
            }
		if(flag != 1){
		System.out.println(0);	
		}

        }


}

//Main class
public class PhoneBook
{	
		public static void main (String[] args) throws FileNotFoundException
	{
		//Creating a new obj of HashTable class called table
		HashTable table = new HashTable();
		
		//calling the hashTable() method in HashTable class which creates an empty hashtable
		table.hashTable();
		
		Scanner scan = new Scanner(System.in);
		Scanner filescanner = new Scanner(new File("PhoneBook.txt"));//txt file contains in the same directory as this file

        	while (filescanner.hasNext()) 
		{
            	String input = filescanner.nextLine();
            		
				//split the line by space
                String[] parts = input.split(" ");
                //first part is name, second is phone no
				String name = parts[0].trim();
				//converting string to int
				Integer no = Integer.parseInt( parts[1].trim());
                
				//compute the hash value and putting to hash table
				table.computeHash(name,no);
        }
        filescanner.close();
		
		System.out.println("*******************************************************************************************");
		System.out.println("Enter the Phone Number Below. Owner of the no will be displayed in the next line if it's in the directory. Otherwise 0 will be displayed.");
		System.out.println("*******************************************************************************************");
		
		int read = scan.nextInt();

		table.search(read);
		
}
}
