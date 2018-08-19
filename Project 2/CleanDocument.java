package cecs328.csulb.edu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;

public class CleanDocument 
{
  private Scanner sc;
  private Scanner scFile;
  private ArrayList<String> ar;
  private File file;
  private int wordCounter;
//--------------------------------------------------------------------------
  public CleanDocument()
  {
    sc = new Scanner(System.in);
    ar = new ArrayList<>();
    wordCounter = 0;
  }
//--------------------------------------------------------------------------
  /**
   * Takes user supplied text document
   * cleans the document
   * (removes all caps, punctuation)
   * @param filename
   */
  public void clean(String input)
  {
    //default clean with list, no frequency
    int k = 0;
    try
    {
      file = new File(input);
      scFile = new Scanner(file);
      while(scFile.hasNext())
      {
        String instring = scFile.next();
        ar.add(instring.replaceAll("[^a-zA-Z ]", "").toLowerCase());
        wordCounter++;
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
  } //end of clean()
//--------------------------------------------------------------------------
  public void cleanDocument(String input)
  {
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
    ArrayList<Token> arToke = new ArrayList<>();
    for(String temp : ar)
    {
      Optional<Token> optoken = arToke.stream()
      .filter(token -> token.getString().equals(temp))
      .findFirst();
      if (optoken.isPresent()) {
        optoken.get().inc();
      }
      else {
        Token toke2 = new Token(temp);
        arToke.add(toke2);
        }
    }
    Comparator<Token> c = Comparator.comparingInt(t -> t.getCount());
    //arToke.sort(c.reversed());
    arToke.sort(c);
    for(Token toke : arToke)
    {
      System.out.println(toke);
    }
  } //end of cleanDocument()
//--------------------------------------------------------------------------
  /**
   * Takes user supplied text document
   * cleans the document
   * inserts each cleaned element into
   * a separate chaining hash table
   * @param filename
   */
  public void cleanSepTable(String filename)
  {
    clean(filename);
    SeparateChainingTableToken st = new SeparateChainingTableToken(wordCounter);
    for(int i = 0; i < ar.size(); i++)
    {
      st.insertElement(ar.get(i));
    }
    st.printTable();
  } //end of cleanSepTable()
//--------------------------------------------------------------------------
  /**
   * Takes user supplied text document
   * cleans the document
   * inserts each cleaned element into
   * a quadratic probing hash table
   * @param filename
   */
  public void cleanQuadTable(String filename)
  {
    clean(filename);
    QuadTableToken qt = new QuadTableToken(wordCounter);
    for(int i = 0; i < ar.size(); i++)
    {
      qt.insertElement(ar.get(i));
    }
    qt.printTable();
  } //end of cleanQuadTable()
//--------------------------------------------------------------------------
} //end of cleanDocument class
