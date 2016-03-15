import java.util.Random;

public class MergeSort {

    private static final Random random = new Random();

    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("There must be exactly two arguments.");
        }

        //arguments [0] - length of random int array to sort
        //          [1] - number of threads to execute sorting

        int arrayLength = Integer.parseInt(args[0]);
        int numberOfThreads = Integer.parseInt(args[1]);

        //produce random dummy array to produce sorting benchmark
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

        int[] A = new int[l];

        for(int i=0; i < l; i++){
            A[i] = random.nextInt();
        }

        return A;
    }
}
