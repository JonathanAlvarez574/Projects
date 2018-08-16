package Assignment8;

public class FHsort
{
   public static < E extends Comparable< ? super E > > 
   void sortInsertion(E[] a)
   {
      int k, pos, arraySize;
      E tmp;
       
      arraySize = a.length;
      for(pos = 1; pos < arraySize; pos++ )
      {
         tmp = a[pos];
         for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
            a[k] = a[k-1];
         a[k] = tmp;
      }
   }

   public static < E extends Comparable< ? super E > > 
   void sortTheShell(E[] a)
   {
      int gap = 1;
      int k, pos, arraySize;
      E tmp;
       
      arraySize = a.length;
      for (gap = arraySize/2;  gap > 0;  gap /= 2)
         for(pos = gap; pos < arraySize; pos++ )
         {
            tmp = a[pos];
            for(k = pos; k >= gap && tmp.compareTo(a[k-gap]) < 0; k -= gap )
               a[k] = a[k-gap];
            a[k] = tmp;
         }
   }
   

   protected static < E extends Comparable< ? super E > >
   void merge(E[] client, E[] working, 
      int leftPos, int rightPos, int rightStop)
   {
      int leftStop, workingPos, arraySize;

      workingPos = leftPos;
      leftStop = rightPos - 1;
      arraySize = rightStop - leftPos + 1;

      while(leftPos <= leftStop && rightPos <= rightStop)
         if(client[leftPos].compareTo(client[rightPos]) < 0 )
            working[workingPos++] = client[leftPos++];
         else
            working[workingPos++] = client[rightPos++];

      while(leftPos <= leftStop)
         working[workingPos++] = client[leftPos++];
      while( rightPos <= rightStop )
         working[workingPos++] = client[rightPos++];


      for( ; arraySize > 0; arraySize--, rightStop-- )
         client[rightStop] = working[rightStop];
   }
   

   protected static < E extends Comparable< ? super E > >  // This merge and sorts internal function
   void sortMerge(E[] a, E[] working, int start, int stop)
   {
      int rightStart;

      if (stop - start < 1)
         return;

      rightStart = (start + stop)/2 + 1;
      sortMerge(a, working, start, rightStart - 1);
      sortMerge(a, working, rightStart, stop);
      merge(a, working, start, rightStart, stop);
   }


   public static < E extends Comparable< ? super E > > //public driver
   void sortMerge(E[] a)
   {
      if (a.length < 2)
         return;

      E[] working = (E[])new Comparable[a.length];
      sortMerge(a, working, 0, a.length - 1);
   }

   protected static < E extends Comparable< ? super E > >
   void filterDown(E[] a, int hole, int arraySize)
   {
      int child;
      E tmp;

      for( tmp = a[hole]; 2 * hole + 1 < arraySize; hole = child )
      {
         child = 2 * hole + 1;

         if( child < arraySize - 1 && a[child].compareTo(a[child + 1]) < 0)
            child++;
         if( tmp.compareTo(a[child]) < 0 )   // MAX heap, not min heap
            a[hole] = a[child];
         else
            break;
      }
      a[hole] = tmp;
   }
   
   // Public driver for the heapsort
   public static < E extends Comparable< ? super E > >
   void sortHeap(E[] a)
   {
      int k, arraySize;
      E temparray;


      arraySize = a.length;  // Ordering the array
      for(k = arraySize/2; k >= 0; k-- )
         filterDown(a, k, arraySize);

      for(k = arraySize - 1; k > 0; k-- )
      {

         temparray = a[0]; // "remove" and placing at end of array
         a[0] = a[k];
         a[k] = temparray;
         filterDown( a, 0, k );  // k represents the shrinking array size
      }
   }
   

   protected static < E extends Comparable< ? super E > >
   E thridMedian(E[] a, int left, int right)
   {
      int center;
      E tmp;

      center = (left + right) / 2;
      if(a[center].compareTo(a[left]) < 0)
         { tmp = a[center]; a[center] = a[left]; a[left] = tmp; }
      if(a[right].compareTo(a[left]) < 0)
         { tmp = a[right]; a[right] = a[left]; a[left] = tmp; }
      if(a[right].compareTo(a[center]) < 0)
         { tmp = a[right]; a[right] = a[center]; a[center] = tmp; }

      tmp = a[center]; a[center] = a[right-1]; a[right-1] = tmp;

      return a[right - 1];
   }
   
   protected static int QS_RECURSION_LIMIT = 15;
   
   public  boolean setRecursionLimit(int newLim)
   {
      if (newLim < 2 || newLim > 1000)
         return false;
      QS_RECURSION_LIMIT = newLim;
      return true;
   }

   protected static < E extends Comparable< ? super E > >
   void quickSort(E[] a, int left, int right)
   {
      E pivot, temparary;
      int i, j;

      if( left + QS_RECURSION_LIMIT <= right )
      {
         pivot = thridMedian(a, left, right);
         for(i = left, j = right - 1; ; )
         {
            while( a[++i].compareTo(pivot) < 0 )
               ;
            while( pivot.compareTo(a[--j]) < 0)
               ;
            if(i < j)
               { temparary = a[i]; a[i] = a[j]; a[j] = temparary; }
            else
               break;
         }


         temparary = a[i]; a[i] = a[right - 1]; a[right - 1] = temparary; // This restores the pivot


         quickSort(a, left, i - 1);     
         quickSort(a, i + 1, right);    
      }
      else

         sortInsertion(a, left, right);  // Non-recursive insertion sort
   }

   protected static < E extends Comparable< ? super E > > 
   void sortInsertion(E[] a, int start, int stop)
   {
      int k, pos;
      E tmp;

      for(pos = start + 1; pos <= stop; pos++ )
      {
         tmp = a[pos];
         for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
            a[k] = a[k-1];
         a[k] = tmp;
      }
   }

   public static < E extends Comparable< ? super E > >
   void quickSort( E[] a )
   {
       quickSort(a, 0, a.length - 1);
   }
}

