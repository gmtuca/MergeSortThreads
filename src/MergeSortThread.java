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

    private MergeSortThread(int[] A, int i, int j, int numberOfThreads) {
        this.A = A;
        this.i = i;
        this.j = j;
        this.numberOfThreads = numberOfThreads;
    }

    public static int[] sort(int[] A, int numberOfThreads){
        //safety checks to pass the unit tests :)
        //won't add significant time to the benchmark
        if(A == null){
            return null;
        }

        if(numberOfThreads <= 0){
            throw new
                    IllegalArgumentException("At least one thread" +
                                             " is required to sort.");
        }

        return sort(A, 0, A.length, numberOfThreads);
    }

    //statically invokes given number of threads to sort array
    private static int[] sort(int[] A, int i, int j, int numberOfThreads){
        if(numberOfThreads > 1){
            // spawn a thread to sort the first half
            Thread t = new MergeSortThread(A, i, (i+j)/2, numberOfThreads/2);
            t.start();

            //recursively sort the second half
            sort(A, (i+j)/2, j, numberOfThreads/2);

            try {
                t.join();
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

        return A;
    }

    @Override
    public void run() {
        sort(A, i, j, numberOfThreads);
    }

    private static int[] merge(int[] A, int l, int r) {
        int[] M = new int[r-l];

        int leftLim = (l+r)/2; //separation between subarrays

        int i1 = l; //current index on first half
        int i2 = leftLim; //current index on second half
        int m = 0; //current index on merged array

        while (i1 < leftLim && i2 < r){
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
