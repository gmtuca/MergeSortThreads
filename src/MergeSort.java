import java.util.Random;

public class MergeSort {

    private static final Random random = new Random();

    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("There must be exactly two arguments.");
            System.exit(1);
        }

        /* arguments [0] - length of random int array to sort
                      [1] - number of threads to execute sorting */

        int arrayLength = 0;
        try{
            arrayLength = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            System.err.println("Array length must be an integer.");
            System.exit(1);
        }

        if(arrayLength < 0){
            System.err.println("Array length must be positive or zero");
            System.exit(1);            
        }

        int numberOfThreads = 0;
        try{
            numberOfThreads = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e){
            System.err.println("Number of threads must be an integer.");
            System.exit(1);
        }

        if(numberOfThreads <= 0){
            System.err.println("Number of threads must be positive.");
            System.exit(1);
        }

        //generate random dummy array to produce sorting benchmark
        int[] A = randomArray(arrayLength);

        long startTime = System.nanoTime();
        MergeSortThread.sort(A, numberOfThreads);
        long endTime = System.nanoTime();
        
        //Calculate performance as 1 / execution_time_in_seconds
        System.out.println(1000000000.0/(endTime - startTime));
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
