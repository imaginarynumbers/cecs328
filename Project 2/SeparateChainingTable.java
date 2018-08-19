package cecs328.csulb.edu;

class Node
{
  String inputString;
  Node next;
  
  public Node(String input)
  {
    inputString = input;
    next = null;
  }
} //end of Node class

public class SeparateChainingTable 
{
  private Node[] sepChain;
  private int capacity;
//---------------------------------------------------------------------------
  public SeparateChainingTable(int size)
  {
    /**
     * User supplies the size of the table
     */
    sepChain = new Node[size];
    capacity = size;
  }
//---------------------------------------------------------------------------
  public int convertHash(int x)
  {
    /**
     * Converts input to a hash code that corresponds to the table size
     */
    int hash = x;
    hash %= capacity;
    if (hash < 0)
        hash += capacity;
    return hash;
  } //end of convertHash
//---------------------------------------------------------------------------
  public int getHashCode(String input)
  {
    /**
     * Converts user input string to a default hash code
     * Sends it to the convertHash method to get proper hash code
     */
    int hash = 0;
    for(int i = 0; i < input.length(); i++)
    {
      hash = 37*hash + input.charAt(i);
    }
    hash %= capacity;
    if(hash < 0)
    {
      //ignore negative hash values
      //hash = Math.abs(hash);
      hash += capacity;
    }
    return hash;
  } //end of getHashCode()
//---------------------------------------------------------------------------
  public void insertElement(String input)
  {
    /**
     * Takes user input String
     * Calls the hash code method to get hash code
     * Checks table for open position
     */
    int tempHash = getHashCode(input);
    Node newNode = new Node(input);
    
    if(sepChain[tempHash] == null)
      {
        sepChain[tempHash] = newNode;
      }
    else
    {
      newNode.next = sepChain[tempHash];
      sepChain[tempHash] = newNode;
    }
    
  } //end of insertElement()
//---------------------------------------------------------------------------
  public void printTable()
  {
    //input into any list object then print
    for(int i = 0; i < sepChain.length; i++)
    {
      Node print = sepChain[i];
      if(print == null)
      {
        System.out.print(i + ": (empty)");
      }
      while(print != null)
      {
        System.out.print(i + ": " + print.inputString + "");
        print = print.next; //advance to next node
        if(print != null)
        {
          System.out.print(", " + print.inputString);
          print = print.next; //advance to next node
        }
      }
      System.out.println();
    }
  } //end of printTable()
//---------------------------------------------------------------------------
} //end of SeparateChainingTable()
