package cecs328.csulb.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
  static CleanDocument cd;
  public static void main(String[] args) 
	{
    Scanner main = new Scanner(System.in);
		boolean whileBool = false;
		long startTime, endTime, finalTime;
		String choice = "";
    System.out.println("Begin Project 2.");
	  while(!whileBool)
	  {
	    main = new Scanner(System.in);
	    System.out.println("\n1.Get Hash Code\n"           +
                         "2.Separate Chaning Table\n"  +
                         "3.Quadratic Probing Table\n" +
                         "4.Clean a Document\n"        +
                         "5.Frequency\n");
	    choice = main.next();
	    switch(choice)
	    {
	      case "1":
	        //get hash code only
	        startTime = System.nanoTime();
	        System.out.println("Enter a String to hash.");
	        String input = main.next();
	        System.out.println("The Hash Code for " + input + " is: " + getHashCode(input));
	        endTime = System.nanoTime();
	        finalTime = endTime - startTime;
	        finalTime = finalTime / 1000000;
	        System.out.println("runtime: " + finalTime + " ms");
	        break;
	      case "2":
	        //separate chaining hash table
          startTime = System.nanoTime();
	        separateChaining();
	        main.nextLine();
          endTime = System.nanoTime();
          finalTime = endTime - startTime;
          finalTime = finalTime / 1000000;
          System.out.println("runtime: " + finalTime + " ms");
	        break;
	      case "3":
	        //quadratic probing hash table
          startTime = System.nanoTime();
	        quadraticProbe();
          endTime = System.nanoTime();
          finalTime = endTime - startTime;
          finalTime = finalTime / 1000000;
          System.out.println("\nruntime: " + finalTime + " ms");
	        break;
	      case "4":
	        //clean document
          startTime = System.nanoTime();
	        cd = new CleanDocument();
	        System.out.println("Enter a document file to clean.");
	        String fileName = main.next();
	        cd.clean(fileName);
          endTime = System.nanoTime();
          finalTime = endTime - startTime;
          finalTime = finalTime / 1000000;
          System.out.println("runtime: " + finalTime + " ms");
	        break;
	      case "5":
	        //frequency analysis
          startTime = System.nanoTime();
	        frequency();
          endTime = System.nanoTime();
          finalTime = endTime - startTime;
          finalTime = finalTime / 1000000;
          System.out.println("runtime: " + finalTime + " ms");
	        break;
	      default:
	        System.out.println("Invalid input.");
	        //choice = main.nextInt();
	        continue;
	    }
	  }
	} //end of main
//--------------------------------------------------------------------------
public static int getHashCode(String input)
{
  /**
   * Converts user input string to a default hash code
   * Sends it to the convertHash method to get proper hash code
   */
  int hash = 0;
  for(int i = 0; i < input.length(); i++)
  {
    //hash = (long) (hash + Math.pow(37, i) + input.charAt(i));
    hash = 37*hash + input.charAt(i);
    if(hash < 0)
    {
      //ignore negative hash values
      hash = Math.abs(hash);
    }
  }
  return hash;
} //end of getHashCode()
//--------------------------------------------------------------------------
public static void separateChaining()
{
  /**
   * Prompts user for size of table
   * User inputs string(s) to hash
   * Insert into a separate chaining hash table
   */
  SeparateChainingTable sep;
  Scanner sc = new Scanner(System.in);
  int tableSize = 0;
  System.out.println("Separate Chaining Hash Table." + "\nPlease specify the table size.");
  tableSize = sc.nextInt(); // Takes user input for the table size
  sep = new SeparateChainingTable(tableSize);
  System.out.println("Enter the String(s) you wish to hash, type quit when done.");
  while(sc.hasNext())
  {
    String input = sc.next();
    if(input.equals("quit"))
      break;
    sep.insertElement(input);
  }
  sep.printTable();
} //end of separateChaining()
//--------------------------------------------------------------------------
public static void quadraticProbe()
{
  /**
   * Prompts user for size of table
   * User inputs string(s) to hash
   * Insert into a quadratic probing hash table
   */
  QuadTable quad;
  Scanner sc = new Scanner(System.in);
  System.out.println("Quadratic Probing Hash Table." + "\nEnter size of table: ");
  int tableSize = sc.nextInt();
  quad = new QuadTable(tableSize);
  System.out.println("Enter a string to hash, type quit when done. ");
  while(true)
  {
    String input = sc.next();
    if(input.equals("quit"))
      break;
    if(!input.equals("quit"))
    {
      quad.insertElement(input);
    }
  }
  quad.printTable(); 
} //end of quadraticProbe()
//--------------------------------------------------------------------------
public static void cleanDocument()
{
  /**
   * Prompts user for text document
   * Clean the document of caps and punctuation
   * Print the cleaned document
   */
  int k = 0;
  File file;
  ArrayList<String> ar = new ArrayList<>();
  Scanner sc = new Scanner(System.in);
  Scanner scFile;
  System.out.println("Enter the filename to clean.");
  String input = sc.next();
  try
  {
    file = new File(input);
    scFile = new Scanner(file);
    while(scFile.hasNext())
    {
      String instring = scFile.next();
      ar.add(instring.replaceAll("[^a-zA-Z ]", "").toLowerCase());
    }
  } 
  catch (FileNotFoundException e)
  {
    System.out.println("File not found.");
  }
  //Print the cleaned document
  System.out.println("print");
  for(int i = 0; i < ar.size(); i++)
  {
    String temp = ar.get(i);
    System.out.print(temp + " ");
    k++;
    if(k == 20)
    {
      System.out.print('\n');
      k = 0;
    }
  }
  System.out.println("\nDocument successfully cleaned!");
} //end of cleanDocument()
//--------------------------------------------------------------------------
public static void frequency()
{
   /**
   * Keep track of frequency of words in a given text document
   * User inputs what data structure they want: list, 
   * Quadratic probing hash table or separate chaining table
   * The output is the frequency of words in decreasing order
   */
  Scanner main = new Scanner(System.in);
  String file = "";
  cd = new CleanDocument();
  System.out.println("Choose the desired data structure." +
      "\n1.List"                           +
      "\n2.Separate Chaining Table"        +
      "\n3.Quadratic Probing Table");
  int choice = main.nextInt();
  switch(choice)
  {
    case 1:
      //list
      System.out.println("Enter the file name to clean");
      file = main.next();
      cd.cleanDocument(file);
      break;
    case 2:
      //separate chaining hash table
      System.out.println("Enter the file name to clean");
      file = main.next();
      cd.cleanSepTable(file);
      break;
    case 3:
      //quadratic probing hash table
      System.out.println("Enter the file name to clean");
      file = main.next();
      cd.cleanQuadTable(file);
      break;
      default:
        System.out.println("Invalid input");
        break;
  } //end of frequency()
}
//--------------------------------------------------------------------------
} //end of program