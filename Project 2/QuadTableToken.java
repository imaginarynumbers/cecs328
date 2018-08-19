package cecs328.csulb.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class QuadTableToken
{
  //global items
  public Token[] tableInput;
  private int capacity;
  private int sizeHolder;
  private int DEFAULT_SIZE = 1000;
  
  /**
   * Default constructor
   */
  public QuadTableToken() 
  {
    tableInput = new Token[DEFAULT_SIZE];
    capacity = DEFAULT_SIZE;
  }
//--------------------------------------------------------------------------
  /**
   * Overloaded constructor
   * Takes user input for size of hash table
   * @param size
   */
  public QuadTableToken (int size)
  {
    //overloaded constructor
    //takes user input for size of table
    capacity = size;
    sizeHolder = 0;
    tableInput = new Token[size];
  } 
//---------------------------------------------------------------------------
  /**
   * Resizes table to the double of the original size
   */
  public void resizeTable()
  {
    //temp table to double size of original table
    int newSize = tableInput.length *2;
    Token[] beforeTable = tableInput;
    tableInput = new Token[newSize];
    
    for(Token t : beforeTable) 
    {
        if (t == null) continue;
        for(int i = 0; i < t.getCount(); i++) {
          insertElement(t.getString());
        }
    }
    //Original table now doubled
  } //end of resizeTable()
//---------------------------------------------------------------------------
  /**
   * Takes user input and hashes into the table using
   * quadratic probing
   * @param input
   */
  public void insertElement(String input)
  {
    //insert new item into the table
    //probe = hash + x^2 % size
    int hash = getHashCode(input);
    int idxStart = convertHash(hash);
    int probe = idxStart;
    int holder = 1;
    int sum = 0;
    while(true)
    {
      //probe = (probe + holder * holder++) % tableInput.length;
      if(probe < 0)
      {
        resizeTable();
        idxStart = convertHash(hash);
        probe = idxStart;
        holder = 1;
      }
      if(tableInput[probe] == null)
      {
        tableInput[probe] = new Token(input);
        sizeHolder++;  
        return;
      }
      else if(tableInput[probe].getString().equals(input))
      {
        tableInput[probe].inc();
        return;
      }
      probe = (idxStart + holder * holder++) % tableInput.length;
    }
  } //end of insertElement()
//---------------------------------------------------------------------------
  /**
   * Converts a string into a hash code
   * @param input
   * @return
   */
  public int getHashCode(String input)
  {
    int hash = 0;
    for(int i = 0; i < input.length(); i++)
    {
      hash = 37*hash + input.charAt(i);
      if(hash < 0)
      {
        //ignore negative hash values
        hash = Math.abs(hash);
      }
    }
    return hash;
  } //end of getHashCode()
//---------------------------------------------------------------------------
  /**
   * Converts into hash code corresponding to the table size
   * @param x
   * @return
   */
  public int convertHash(int x)
  {
    int hashVal = x;
    hashVal %= capacity;
    if (hashVal < 0)
        hashVal += capacity;
    return hashVal;
  } //end of convertHash()
//--------------------------------------------------------------------------
  /**
   * Prints the hash table to the system output
   */
  public void printTable()
  {
    int counter = 0;
    //input into a any list object, sort then print
    ArrayList<Token> arToke = new ArrayList<>();
    for(int i = 0; i < tableInput.length; i++)
    {
      if (tableInput[i] != null) {
        arToke.add(tableInput[i]);
      }
    }
    Comparator<Token> c = Comparator.comparingInt(t -> t.getCount());
    //arToke.sort(c.reversed());
    arToke.sort(c);
    for(Token toke : arToke)
    {
      System.out.println(toke);
    }
  } //end of printTable()
//---------------------------------------------------------------------------
} //end of QuadTableToken class
