package cecs328.csulb.edu;

public class QuadTable
{
  //global items
  public String[] tableInput;
  private int capacity;
  private int sizeHolder;
  private final int DEFAULT_SIZE = 1000;
  
  /**
   * Default constructor
   */
  public QuadTable() 
  {
    tableInput = new String[DEFAULT_SIZE];
    capacity = DEFAULT_SIZE;
  } //end of default constructor
//--------------------------------------------------------------------------
  /**
   * Overloaded constructor
   * Takes user input for size of hash table
   * @param size
   */
  public QuadTable (int size)
  {
    //overloaded constructor
    //takes user input for size of table
    capacity = size;
    sizeHolder = 0;
    tableInput = new String[size];
  } //end of overloaded constructor
//---------------------------------------------------------------------------
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
    int temp = getHashCode(input);
    int probe = 0;
    int holder = 0;
    int sum = 0;
    while(probe != temp)
    {
      probe = (probe + holder * holder++) % tableInput.length;
      if(probe < 0)
      {
        resizeTable();
        probe = -probe;
      }
        
      if(tableInput[probe] == null)
      {
        tableInput[probe] = input;
        sizeHolder++;
        return;
      }
    }
  } //end of insertElement()
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
    for (int i = 0; i < tableInput.length; i++)
    {
      if(tableInput[i] != null)
      {
        System.out.print(tableInput[i] + ", ");
      }
      if(tableInput[i] == null)
      {
        System.out.print("[EMPTY], ");
      }
      if(tableInput.length > 50)
      {
        if(counter == 20)
        {
          System.out.println("\n");
          counter = 0;
        }
      }
      
    }
  } //end of printTable()
//---------------------------------------------------------------------------
} //end of QuadTable class
