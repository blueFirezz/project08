package FHsort;

import java.io.*;
import java.util.Random;

/**
 * Tests different recursive limits of arrays
 */
public class FHsortTest2 {

    /**
     * Creates and Fills an Array with random numbers based on the size of the array
     * @param arraySize How big the array needs to be
     * @return Returns the filled array tha is ready to be sorted
     */
    private static Integer[] createRandArray(int arraySize){
        Random rand = new Random();
        Integer[] randVals = new Integer[arraySize];
        for(int i = 0; i < arraySize; i++){
            randVals[i] = rand.nextInt(arraySize);
        }

        return randVals;
    }

    /**
     * Test Client
     * @param args
     * @throws IOException For FileWriter
     */
    public static void main(String[] args) throws IOException {
        //File name to store output values in CSV format
        String fileName = "resources/testOutput1.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));

        //Array of different sizes to test the recursion limits (20k - 10M)
        int[] arraySizes = new int[]
                {20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 150000,
                        200000, 300000, 400000, 500000, 600000, 1000000, 1500000, 5000000, 7500000, 10000000};

        System.out.println("=====================================================================================\n"
                + "==================================== TEST CASE " +  1  + " ====================================");

        //Loops through the different array sizes (20k - 10M)
        for (int arraySize : arraySizes) {
            //Creates and Fills an array with random numbers to test sorting
            Integer[] randomValues = createRandArray(arraySize);
            //Loops through the different recursion limits (2 - 300 by evens)
            for (int i = 2; i <= 300; i += 2) {
                //Starts a timer
                double tStart = System.currentTimeMillis();
                String output = "";
                //sets recursion limit
                FHsort.setRecursionLimit(i);
                //Adds recursion limit to output String
                output += i + ",";
                //Quick Sorts array
                FHsort.quickSort(randomValues);
                //Ends timer
                double tEnd = System.currentTimeMillis();
                //Finalizes string with array size sorted and the time it took in seconds
                output += arraySize + "," + ((tEnd - tStart) / 1000);

                //Outputs to both client and a text document to converted to a CSV format file
                writer.println(output);
                System.out.println(output);
            }
        }

        //Closes the file writer
        writer.close();

        System.out.println("========================== END OF TEST CASE " +  1  + " ==========================\n\n\n");
    }
}
