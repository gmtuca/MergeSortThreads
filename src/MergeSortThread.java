import java.util.Arrays;

public class MergeSortThread extends Thread{

    private int i, j, numberOfThreads;
    private int[] A;

    public MergeSortThread(int[] A, int i, int j, int numberOfThreads) {
        this.A = A;
        this.i = i;
        this.j = j;
        this.numberOfThreads = numberOfThreads;
    }

    public static int[] sort(int[] A, int numberOfThreads){
        if(A == null){
            return null;
        }

        if(numberOfThreads <= 0){
            throw new IllegalArgumentException("At least one thread is required to sort.");
        }

        Thread t = new MergeSortThread(A, 0, A.length, numberOfThreads);

        //run is called on purpose (not start)
        t.run();

        return A;
    }

    @Override
    public void run() {
        if(numberOfThreads > 1){
            Thread t1 = new MergeSortThread(A, i,           (i+j)/2, numberOfThreads/2);
            Thread t2 = new MergeSortThread(A, (i+j)/2,     j,       numberOfThreads/2);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            }
            catch (InterruptedException e){
                System.err.println("Oh well...");
            }

            int[] m = merge(A, i, j);

            System.arraycopy(m,0,A,i,m.length);
        }
        else{
            Arrays.sort(A, i, j);

        }
    }

    public static int[] merge(int[] A, int l, int r) {
        int[] M = new int[r-l];

        int leftLim = (l+r)/2;

        int i1 = l;
        int i2 = leftLim;
        int m = 0;

        while (i1 < leftLim && i2 < r)
        {
            if (A[i1] < A[i2])
            {
                M[m] = A[i1];
                i1++;
            }
            else
            {
                M[m] = A[i2];
                i2++;
            }
            m++;
        }

        System.arraycopy(A, i1, M, m, leftLim - i1);
        System.arraycopy(A, i2, M, m, r - i2);

        return M;
    }
}
