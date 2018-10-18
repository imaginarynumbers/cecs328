package cecs328.csulb.edu;
/**
 * @author Kacy Rowe
 * CECS 328 - Data Structures and Algorithms
 * Merge Sort Project
 * Professor Ali Sharifian
 */
public class MergeSort
{
  /**
   * @param args
   */
  public static int comparison = 0;
  public static void main(String[] args)
  {
    //20 int values for testing, also has duplicate values
    int[] array = {5, 12, 99, 34, 4542, 32, 222, 3, 5, 5, 100, 420, 22, 2145, 4, 33, 32, 1, 54, 78};
    
    //Single int value for testing
    //int[] array = {5};
    
    //print the values in the array
    System.out.println("The unsorted array values are:");
    printArray(array, 0, array.length - 1);
    
    //call the merge sort method
    merge_sort(array, 0, array.length - 1);

    System.out.println("\n\nThe sorted " + array.length + " array values are:");
    //print the sorted int values
    printArray(array, 0, array.length - 1);
    System.out.println("\n\nRequired " + comparison + " comparisons.");
  }
//---------------------------------------------------------------------------
  /**
   * Recursive Merge Sort method
   * @param array
   * @param p
   * @param r
   */
  public static void merge_sort(int[] array, int p, int r)
  { 
    if(p < r)
     {
       int q = ((p+r)/2);
       merge_sort(array, p, q);
       merge_sort(array, q + 1, r);
       merge(array, p, q, r);
     }
  }
//---------------------------------------------------------------------------
  /**
   * Merge method after the array gets broken down completely
   * @param array
   * @param p
   * @param q
   * @param r
   */
  public static void merge(int[] array, int p, int q, int r)
  {
     //This algorithm was provided by the class textbook
     //Intro to Algorithms 3rd edition by Cormen, Leiserson, Rivest, Stein respectively.
     int n1 = q - p + 1;
     int n2 = r - q;
     int[] new_left_array = new int[n1 + 1];
     int[] new_right_array = new int[n2 + 1];
     for(int i = 0; i < n1; i++)
     {
       new_left_array[i] = array[p + i];
     }
     for(int j = 0; j < n2; j++)
     {
       new_right_array[j] = array[q + j + 1];
     }
     
     //Sentinel values (depicted as infinity in book)
     new_left_array[n1] = Integer.MAX_VALUE;
     new_right_array[n2] = Integer.MAX_VALUE;
     
     int i = 0;
     int j = 0;
     
     for(int k = p; k <= r; k++)
     {
       
       if(new_left_array[i] <= new_right_array[j])
       {
         array[k] = new_left_array[i];
         i = i + 1;
         comparison++; //Keep track of how many comparisons are done
       }
       else 
       {
         array[k] = new_right_array[j];
         j = j + 1;
       }
     }
  }
//---------------------------------------------------------------------------
  /**
   * Simple array printing method with []'s
   * @param arr
   * @param p
   * @param q
   */
  public static void printArray(int[] arr, int p, int q) {
    System.out.print("[");
    for(int k = p; k < q; k++) {
      System.out.print(arr[k] + ", ");
    }
    System.out.print(arr[q] + "]");
  }
} //end of program
