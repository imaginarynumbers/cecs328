/**
 * 
 */
package cecs328.csulb.edu;

/**
 * @author Kacy Rowe
 * CECS 328 - Build Max Heap Project
 * Professor Ali Sharifian
 *
 */
public class BuildMaxHeap
{
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    int array[] = {12, 99, 34, 4542, 32, 222, 3, 5, 5, 100, 420, 22, 2145, 4, 33, 32, 1, 54, 78 , 99};
    //int array[] = {4, 5, 1, 7, 3, 3, 8};
    //int array[] = {12, 6, -2, -5, 22, 4, 8, 1};
    //int array[] = {5}; //array with single value for testing, professors request
    System.out.println("The values in the array before:");
    printArray(array);
    System.out.println("\n\nBuilding max heap...\n");
    buildMaxHeap(array);
    System.out.println("The values in the array after:");
    printArray(array);
    //Calculate the height of the tree using lg(array.length)
    int heightOfTreeValue = (int) Math.floor(Math.log(array.length)/Math.log(2.0));
    System.out.println("\nWith height: " + heightOfTreeValue);
  }
//---------------------------------------------------------------------------
  /**
   * Recursive Max Heapify method
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
      largest = left;
    else
      largest = i;
    if(right < array.length && array[right] > array[largest])
      largest = right;
    if (largest != i)
    {
      int temp = array[i];
      array[i] = array[largest];
      array[largest] = temp;
      max_heapify(array,largest);
    }
  }
//---------------------------------------------------------------------------
  /**
   * Build Max Heap method
   * @param array
   */
  public static void buildMaxHeap(int[] array)
  {
    for(int i = array.length / 2; i >= 0; i--)
    {
      max_heapify(array,i);
    }
  }
//---------------------------------------------------------------------------
  public static int left(int input)
  {
    return 2 * input + 1;
  }
//---------------------------------------------------------------------------
  public static int right(int input)
  {
    return 2 * input + 2;
  }
//---------------------------------------------------------------------------
  /**
   * Simple print array method
   * @param array
   */
  public static void printArray(int[] array)
  {
    for(int i = 0; i < array.length; i++)
    {
      System.out.print(array[i] + ", " );
    }
  }
//---------------------------------------------------------------------------
}
