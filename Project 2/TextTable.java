package cecs328.csulb.edu;

public class TextTable
{
  //global items
  public String[] tableInput;
  private int capacity;
  private int sizeHolder;
  
  /**
   * Default constructor
   */
  public TextTable() 
  {
    //Not used
  }
//--------------------------------------------------------------------------
  /**
   * Overloaded constructor
   * Takes user input for size of hash table
   * @param size
   */
  public TextTable (int size)
  {
    //overloaded constructor
    //takes user input for size of table
    capacity = size;
    sizeHolder = 0;
    tableInput = new String[size];
  }
  /**
   * Resizes table to the double of the original size
   */
  public void resizeTable()
  {
    //temp table to double size of original table
    String[] newTable = new String[tableInput.length*2];
    for(int i = 0; i < tableInput.length; i++) 
    {
      newTable[i] = tableInput[i];
    }
    //Original table now doubled
    tableInput = new String[newTable.length];
    for(int i = 0; i < tableInput.length; i++) 
    {
      tableInput[i] = newTable[i];
    }
  }
//---------------------------------------------------------------------------
  /**
   * Takes user input and hashes into the table using
   * quadratic probing
   * @param input
   */
  public void insertElement(String input)
  {
    //insert new item into the table
    int temp = getHashCode(input);
    int probe = 0;
    int holder = 0;
    int sum = 0;
    while(true)
    {
      probe = (probe + holder * holder++) % tableInput.length;
      if(probe < 0)
        probe = -probe;
      if(tableInput[probe] == null)
      {
        tableInput[probe] = input;
        sizeHolder++;
        if(sizeHolder == Math.floorDiv(3*tableInput.length, 4))
          resizeTable();
        return;
      }
    }
  }
//---------------------------------------------------------------------------
  /**
   * Converts a string into a hash code
   * then calls the convertHash method
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
    hash = convertHash(hash);
    return hash;
  }
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
  }
//--------------------------------------------------------------------------
  /**
   * Prints the hash table to the system output
   */
  public void printTable()
  {
    for (int i = 0; i < tableInput.length; i++)
    {
      if(tableInput[i] != null)
      {
        System.out.print(tableInput[i] + " ");
      }
      if(tableInput[i] == null)
      {
        System.out.print("[ ], ");
      }
      
    }
  }
//---------------------------------------------------------------------------
}
