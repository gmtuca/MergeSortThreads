import java.util.Arrays;

public class MergeSortThread extends Thread{

    /*
        i = starting index for this thread to sort, inclusive
        j = ending index for this thread to sort, exclusive
        numberOfThreads = total number of children (directly or indirectly)
                          spawned by this instance
        A = int array to sort in-place
    */

    private int i, j, numberOfThreads;
    private int[] A;

    public MergeSortThread(int[] A, int i, int j, int numberOfThreads) {
        this.A = A;
        this.i = i;
        this.j = j;
        this.numberOfThreads = numberOfThreads;
    }

    //statically invokes given number of threads to sort array
    public static int[] sort(int[] A, int numberOfThreads){
        if(A == null){
            return null;
        }

        if(numberOfThreads <= 0){
            throw new
                    IllegalArgumentException("At least one thread" +
                                             " is required to sort.");
        }

        /* Instantiate first thread used to start the sorting process ("root")
           It will sort array A from start (0) to end (A.length)
           with numberOfThreads threads */
        Thread t = new MergeSortThread(A, 0, A.length, numberOfThreads);

        /* Once it completes, it means the whole array will be sorted
           thus I call .run() instead of .start() and .join() here */

        t.run();

        return A;
    }

    @Override
    public void run() {
        if(numberOfThreads > 1){
            /* spawn t1 to sort first half of the array with half
               of the horsepower (ie. numberOfThreads/2)
               spawn t2 to sort second half of the array with half
               of the horsepower (ie. numberOfThreads/2) */

            Thread t1 = new MergeSortThread(A, i, (i+j)/2, numberOfThreads/2);
            Thread t2 = new MergeSortThread(A, (i+j)/2, j, numberOfThreads/2);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            }
            catch (InterruptedException e){
                System.err.println("Oh well...");
            }

            /* After both threads have sorted their sections, merge
               them (knowing they are separated at (i+j)/2).
               The merge cannot be done in-place, so copy it back to A */
            int[] m = merge(A, i, j);

            System.arraycopy(m,0,A,i,m.length);
        }
        else{
            /* Base case: there are no threads left to spawn, thus the
               current thread must sort its section of the array sequentially */
            Arrays.sort(A, i, j);

        }
    }

    public static int[] merge(int[] A, int l, int r) {
        int[] M = new int[r-l];

        int leftLim = (l+r)/2; // separation between subarrays

        int i1 = l; //current index on first half
        int i2 = leftLim; //current index on second half
        int m = 0; //current index on merged array

        while (i1 < leftLim && i2 < r) {
            if (A[i1] < A[i2]) {
                M[m] = A[i1];
                i1++;
            }
            else {
                M[m] = A[i2];
                i2++;
            }
            m++;
        }

        /* if there are any elements left on the first
           or second half, move them to the merged array */
        System.arraycopy(A, i1, M, m, leftLim - i1);
        System.arraycopy(A, i2, M, m, r - i2);

        return M;
    }
}
