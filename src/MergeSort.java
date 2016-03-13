import java.util.Random;

public class MergeSort {

    private static final Random random = new Random();

    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("There must be exactly two arguments.");
        }

        int arrayLength = Integer.parseInt(args[0]);
        int numberOfThreads = Integer.parseInt(args[1]);

        int[] A = randomArray(arrayLength);

        long startTime = System.nanoTime();
        MergeSortThread.sort(A, numberOfThreads);
        long endTime = System.nanoTime();

        System.out.println(endTime-startTime);
    }

    public static int[] randomArray(int l){
        if(l < 0){
            return null;
        }

        int[] V = new int[l];

        for(int i=0; i < l; i++){
            V[i] = random.nextInt();
        }

        return V;
    }
}
