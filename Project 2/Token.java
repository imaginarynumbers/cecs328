package cecs328.csulb.edu;

public class Token {
  
  private String token;
  private int count;

  public Token(String token) 
  {
    this.token = token;
    count = 1;
  }
//--------------------------------------------------------------------------
  public String getString() 
  {
    return token;
  }
//--------------------------------------------------------------------------
  public void inc() 
  {
    count++;
  }
//--------------------------------------------------------------------------
  public int getCount() 
  {
    return count;
  }
//--------------------------------------------------------------------------
  @Override
  public String toString()
  {
     return String.format("Key: %-10s Count:%d", getString(), getCount());
  }
}
