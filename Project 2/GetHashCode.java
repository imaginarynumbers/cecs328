package cecs328.csulb.edu;

public class GetHashCode 
{
//---------------------------------------------------------------------------
  public GetHashCode()
  {
    //unused default constructor
  }
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
      //hash function
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
} //end of GetHashCode class
