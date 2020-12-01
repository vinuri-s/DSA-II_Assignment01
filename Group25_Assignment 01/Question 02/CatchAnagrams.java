import java.util.*;
import java.io.*;

class HashTable{//Create a class for hashtable-Initialize
    static int SIZE=250;int[] key;String[] value;int count=0;
 
    public HashTable(){//construct a hashtable
        key = new int[SIZE];
        value = new String[SIZE];
    }
    
    private int asciiVal(String word){//get the totalasciivalues (int values) of  words
        int asciiVal=0;int i;
        for(i=0;i<word.length();i++){
            asciiVal=asciiVal+word.charAt(i);
        }
        return asciiVal;
    }   

    private int hashFunction(int asciiVal){ //getting hash codes of words to facilitate hashing in hash tables
        return(0*asciiVal) % SIZE ;
    }
   
    public void insert(String words){//insert list of words to the hash table
        count++;
        int asciiVal = asciiVal(words);
        int hash = hashFunction(asciiVal);
        int i = hash;
        do{
            if(key[i]==0){
                key[i]=asciiVal;value[i]=words;
                count++;
                return;
            }      
            i=(i+1)%SIZE;
        }while (i != hash);
    }

    public void printAnagrams(){//print listed words together
        for(int i=0; i<SIZE; i++){
            int temp = 0;int j;
            if (key[i] != 0){
                for(j=i+1; j<SIZE; j++){
                    if(key[i] == key[j]){//check if the values of two words are same
                        char wordArr1[] = value[i].toCharArray();
                        Arrays.sort(wordArr1);
                        char wordArr2[] = value[j].toCharArray();
                        Arrays.sort(wordArr2);//if same put them into these two arrays

                        if(Arrays.equals(wordArr1, wordArr2)){//then if those arrays are equal
                            if(temp == 0){
                                System.out.print(value[i] + " ");
                            }
                            System.out.print(value[j] + " ");//print them in a row
                            key[j] = 0;
                            temp++;
                        }
                    }
                }
            }
            if(temp > 0){
                System.out.println();//put a space after  one anagram set printed
            }
        }
    }
}
class CatchAnagrams{
    public static void main(String[] args) throws FileNotFoundException{	
        
        HashTable word = new HashTable();   //call a hashtable   function
        File file = new File("wordlist.txt");
        Scanner input = new Scanner(file);//input the wordlist.txt file

        while(input.hasNext()){
            word.insert(input.next());//insert all the words 
        }  
        input.close();
        word.printAnagrams();// call print angram function and display output
        
	}
}