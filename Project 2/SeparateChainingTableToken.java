package cecs328.csulb.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

class NodeToken
{
  Token value;
  NodeToken next;
  
  public NodeToken(Token input)
  {
    value = input;
    next = null;
  }
  public void incToke()
  {
    value.inc();
  }
} //end of NodeToken class

public class SeparateChainingTableToken 
{
  private NodeToken[] sepChain;
  private int capacity;
//---------------------------------------------------------------------------
  public SeparateChainingTableToken(int size)
  {
    /**
     * User supplies the size of the table
     */
    sepChain = new NodeToken[size];
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
  } //end of convertHash()
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
    //hash = convertHash(hash);
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
    

      NodeToken temp = sepChain[tempHash];
      while(temp != null)
      {
        if(temp.value.getString().equals(input))
        {
          temp.incToke();
          return;
        }
        else
        {
          temp = temp.next;
        }
      }
      NodeToken newNode = new NodeToken(new Token(input));
      newNode.next = sepChain[tempHash];
      sepChain[tempHash] = newNode;
    
    } //end of insertElement()
//---------------------------------------------------------------------------
  public void printTable()
  {
    ArrayList<Token> arToke = new ArrayList<>();
    for(int i = 0; i < sepChain.length; i++)
    {
      if (sepChain[i] != null) {
        NodeToken temp = sepChain[i];
        while(temp != null)
        {
          arToke.add(temp.value);
          {
            temp = temp.next;
          }
        }
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
} //end of SeparateChainingTableToken class
