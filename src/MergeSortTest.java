import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gmtuca on 13/03/16.
 */
public class MergeSortTest {

    @Test
    public void testNullArrayOneThread(){
        assertNull(MergeSortThread.sort(null,1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSortArrayZeroThreads(){
        MergeSortThread.sort(new int[]{1,2,3},0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSortArrayNegativeThreads(){
        MergeSortThread.sort(new int[]{1,2,3},-1);
    }

    @Test
    public void testSortEmptyArrayOneThread(){
        assertEquals(MergeSortThread.sort(new int[0],1).length, 0);
    }

    @Test
    public void testSortSingleElementArrayOneThread(){
        assertEquals(MergeSortThread.sort(new int[]{123},1)[0], 123);
    }

    @Test
    public void testSortSingleElementArrayTwoThreads(){
        assertEquals(MergeSortThread.sort(new int[]{123},2)[0], 123);
    }

    @Test
    public void testSortTwoElementsArrayOneThread(){
        int[] A = new int[]{1,-1};
        assertSorted(MergeSortThread.sort(A,1));
    }

    @Test
    public void testSortTwoElementsArrayTwoThreads(){
        int[] A = new int[]{1,-1};
        assertSorted(MergeSortThread.sort(A,2));
    }

    @Test
    public void testSortTwoElementsArrayThreeThreads(){
        int[] A = new int[]{1,-1};
        assertSorted(MergeSortThread.sort(A,2));
    }

    @Test
    public void testSortShortArrayInvertedTwoThreads(){
        int[] A = new int[]{100,50,20,10,5,3,1,-1};
        assertSorted(MergeSortThread.sort(A,2));
    }

    @Test
    public void testSortShortArraySortedTwoThreads(){
        int[] A = new int[]{1,2,3,4,5};
        assertSorted(MergeSortThread.sort(A,2));
    }

    @Test
    public void testSortRandomArrayOneThread(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,1));
    }

    @Test
    public void testSortRandomArrayTwoThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,2));
    }

    @Test
    public void testSortRandomArrayFiveThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,5));
    }

    @Test
    public void testSortRandomArrayFiftyThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,50));
    }

    @Test
    public void testSortRandomArraySeventyFiveThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,75));
    }

    @Test
    public void testSortRandomArrayHundredThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,100));
    }

    @Test
    public void testSortRandomArrayHundredOneThreads(){
        int[] A = MergeSort.randomArray(100);
        assertSorted(MergeSortThread.sort(A,101));
    }

    @Test
    public void testSortLongRandomArrayOneThread(){
        int[] A = MergeSort.randomArray(10000);
        assertSorted(MergeSortThread.sort(A,1));
    }

    @Test
    public void testSortLongRandomArrayThreeThreads(){
        int[] A = MergeSort.randomArray(10000);
        assertSorted(MergeSortThread.sort(A,3));
    }

    @Test
    public void testSortLongRandomArrayEightThreads(){
        int[] A = MergeSort.randomArray(10000);
        assertSorted(MergeSortThread.sort(A,8));
    }

    @Test
    public void testSortTenMillionEntriesRandomArrayEightThreads(){
        int[] A = MergeSort.randomArray(10000000);
        assertSorted(MergeSortThread.sort(A,8));
    }


    private static void assertSorted(int[] A){
        assertTrue(isSorted(A));
    }

    private static boolean isSorted(int[] A){
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }
}