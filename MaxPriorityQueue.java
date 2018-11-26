package cecs328.csulb.edu;

import java.util.Scanner;

// Project 4 - Max Priority Queue
// CECS 328 - Data Structures and Algorithms
// Professor Ali Sharifian
// Written by: Kacy Rowe

public class MaxPriorityQueue 
{
  //must have at least 10 integers
  public static int[] array = new int[] {5, 10, 8, 13, 2, 17, 12, 3, 1, 55, 1000};
  //single integer to test
  //public static int[] array = new int[] {1000}; 
  public static void main(String[] args) 
  {
    System.out.println("Input array:");
    printArray(array);
    System.out.println();
    buildMaxHeap(array);
    Scanner sc = new Scanner(System.in);
    while(true)
    {
      //begin menu
      System.out.println("\nChoose from the following options:" +
                           "\n1. Insert"                        +
                           "\n2. Maximum"                       +
                           "\n3. Extract-Max"                   +
                           "\n4. Increase-Key"                  +
                           "\n5. Exit"                          );
      System.out.println("\nMax heap:");
      printArray(array);
      int choice = sc.nextInt(); //take user input
      switch(choice)
      {
        case 1:
          //Insert
          System.out.println("\nInput number to insert.");
          int userInputToQueue = sc.nextInt();
          insert(userInputToQueue);
          printArray(array);
          break;
        case 2:
          //Maximum
          System.out.println("\nMax: " + maximum(array));
          break;
        case 3:
          //Extract-Max
          extract_max(array);
          break;
        case 4:
          //Increase-key
          System.out.println("Which node would you like to modify?\nChoose between: 1-" + array.length);
          int nodeChange = sc.nextInt();
          System.out.println("Enter new value for node: " + nodeChange);
          int nodeValue = sc.nextInt();
          increase_key(nodeChange - 1, nodeValue);
          break;
        case 5:
          //Exit
          System.out.println("\nGoodbye.");
          sc.close();
          System.exit(0);
          break;
        default:
          System.out.println("\nInvalid input, try again");
      }
    }
  }
//---------------------------------------------------------------------
  /**
   * Inserts a new value into the heap
   * @param input
   */
  public static void insert(int input)
  {
    //inserts element input into the array
    int[] temp = new int[array.length + 1];

    for (int i = 0; i < array.length; i++)
    {
      //array = Arrays.copyOf(temp, i + 1);
      temp[i] = array[i];
    }
    temp[array.length] = input;
    array = new int[temp.length];
    for (int i = 0; i < array.length; i++)
    {
      //array = Arrays.copyOf(temp, i + 1);
      array[i] = temp[i];
    }
    if (array[0] < input)
    {
      max_heapify(array,input);
    }
    if(array[0] > input)
    {
      max_heapify(array, get_max(array));
    }
    buildMaxHeap(array);
  }
//---------------------------------------------------------------------
  /**
   * Returns the maximum of the heap.
   * @param array
   * @return
   */
  public static int maximum(int[] array)
  {
    //returns the element with the largest key
    return array[0];
  }
//---------------------------------------------------------------------
  /**
   * Increases a specific key in the heap with a new one.
   * @param i
   * @param key
   */
  public static void increase_key(int i, int key)
  {
    //increases the value of element x's key to the new value k,
    //which is assumed to be at least as large as x's current key value
    int tempi = i;
    int tempKey = key;
    Scanner sc2 = new Scanner(System.in);
    try 
    {
      //check to see if user went out of bounds of the array
      if(key < array[i] || tempi < 0);
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      //prompt user for input again
      System.out.println("You went out of bounds of the array, try again.");
      System.out.println("Which node would you like to modify?");
      int nodeChange = sc2.nextInt();
      tempi = nodeChange;
      System.out.println("Enter new value for node: " + nodeChange);
      int nodeValue = sc2.nextInt();
      tempKey = nodeValue;
      increase_key(nodeChange, nodeValue);
    }
    if(tempi >= 0)
    {
      if(key < array[tempi])
        System.out.println("\nNew key is smaller than current key");
      if(key > array[tempi])
      {
        array[tempi] = tempKey;
        max_heapify(array, get_max(array));
      }
      else if(tempi < 0)
        System.out.println("You went out of bounds of the array, try again");
    }
    max_heapify(array, get_max(array));
  }
//---------------------------------------------------------------------
  /**
   * Resizes array after extract max is called
   */
  public static void resize_array_delete_max()
  {
    int[] tempArray = new int[array.length - 1];
    for(int i = 0; i < array.length - 1; i++)
    {
      tempArray[i] = array[i];
    }
    array = new int[array.length - 1];
    for(int i = 0; i < array.length; i++)
    {
      if(i > 0)
      {
        array[i - 1] = tempArray[i];
      }
    }
    buildMaxHeap(array);
  }
//---------------------------------------------------------------------
  /**
   * Searches the heap to find the largest value for other methods
   * @param array
   * @return
   */
  public static int get_max(int[] array)
  {
    int max = 0;
    for(int i = 0; i < array.length; i++)
    {
      if(array[i] > max)
        max = array[i];
    }
    return max;
  }
//---------------------------------------------------------------------
  /**
   * Extracts the largest value in the heap
   * @param array
   */
  public static void extract_max(int[] array)
  {
    //removes and returns the element S with the largest key
    if(array.length < 1)
    {
      System.out.println("\nError, heap underflow.");
    }
    else
    {
      resize_array_delete_max();
      max_heapify(array, get_max(array));
    }
  }
//---------------------------------------------------------------------
  /**
   * Takes the heap and makes it a max heap, called by BuildMaxHeap
   * @param array
   * @param i
   */
  public static void max_heapify(int[] array, int i)
  {
    //max heapify method
    int largest = i;
    int left = left(i);
    int right = right(i);
    if(left < array.length && array[left] > array[i])
    {
      largest = left;
    }
    else
      largest = i;
    if(right < array.length && array[right] > array[largest])
      largest = right;
    if(largest != i)
    {
      int tempValue = array[i];
      array[i] = array[largest];
      array[largest] =  tempValue;
      max_heapify(array, largest);
    }
  }
//---------------------------------------------------------------------
  /**
   * Builds the max heap
   * @param array
   */
  public static void buildMaxHeap(int[] array)
  {
    for(int i = array.length / 2; i>= 0; i--)
    {
      max_heapify(array,i);
    }
  }
//---------------------------------------------------------------------
  /**
   * Get the right child of a node
   * @param i
   * @return
   */
  private static int right(int i) 
  {
    return 2 * i + 2;
  }
//---------------------------------------------------------------------
  /**
   * Get the left child of a node
   * @param i
   * @return
   */
  private static int left(int i) 
  {
    return 2 * i + 1;
  }
//---------------------------------------------------------------------
  /**
   * Simple array print method
   * @param array
   */
  public static void printArray(int[] array)
  {
    if(array.length < 1)
    {
      System.out.println("Heap is empty.");
    }
    if(array.length >= 1)
    {
      for(int i = 0; i < array.length; i++)
      {
          System.out.print(array[i] + " ");
      }
    }
  }
//---------------------------------------------------------------------
}
////////////////////////////////////////////////////////////////////////