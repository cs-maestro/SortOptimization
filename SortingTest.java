import java.util.*;

public class SortingTest {

    ///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        // check for validity
        for (int i = 0; i < 100; i++) {

            var x = new double[5000];

            // fill the array
            for (int j = 0; j < x.length; j++) {

            x[i] = (Math.random() * 100);

            }

            var y = x.clone();

            mySorting(x);

            Arrays.sort(y);

            if (!Arrays.equals(x, y)) {

                System.out.println("Error found at index " + i);
                return;

            }
        }

        // Test Array 1
        var test1 = new double[100000];

        for (int i = 0; i < test1.length; i++) {

            test1[i] = (Math.random() * 100);

        }

        // Test Array 2
        var test2 = test1.clone();

        // start the time
        double timeStart = System.nanoTime();

        // sort Test Array 1 with my method
        mySorting(test1);

        // marks the time after one sorting as lapOne
        double lapOne = System.nanoTime();

        // sort Test Array 1 with insersion sort method
        sort(test2);

        // marks the time after second sorting as lapTwo
        double lapTwo = System.nanoTime();

        // find sorting times
        double mySortingTime = (lapOne - timeStart);
        double sortTime = (lapTwo - lapOne);

        // check if our sorting method performed well
        // mention the difference in seconds
        if (mySortingTime < sortTime) {

            System.out.println("\nWe won the race.\nDifference: " +
            ((sortTime - mySortingTime) / 1e9) + " seconds\n");
        }
        else {

            System.out.println("\nNeeds improvement.\nDifference: " +
            ((mySortingTime - sortTime) / 1e9) + " seconds");

        }
    }

    ///////////////////////////////////////////////////////////////////////////

    private static void mySorting(double[] x) {

        var a = new ArrayList<Double>();

        // take each number of input array one by one into an empty ArrayList
        // sort the numbers in ArrayList as they are taken from input array
        for (int i = 0; i < x.length; i++) {

            int sortedIndex = 0;

            int upperBoundIndex = a.size() - 1;
            int lowerBoundIndex = 0;
            int midIndex = a.size() / 2;

            outerLoop: for
            (int j = 0; j <= (Math.log(a.size()) / Math.log(2)); j++) {

                // check if the number fits in left half
                if (a.get(midIndex) > x[i]) {

                    if (midIndex > lowerBoundIndex && a.get(midIndex - 1) <= x[i]) {
                        sortedIndex = midIndex;
                        break outerLoop;
                    }

                    upperBoundIndex = midIndex;
                    midIndex = (lowerBoundIndex + upperBoundIndex) / 2;

                    if (lowerBoundIndex == midIndex
                    || upperBoundIndex == midIndex) {

                        if (a.get(lowerBoundIndex) >= x[i]) {
                            sortedIndex = 0;
                            break outerLoop;
                        }
                        else {
                            sortedIndex = 1;
                            break outerLoop;
                        }
                    }
                }
                // check if the number fits in right half
                else if (a.get(midIndex) < x[i]) {

                    if (midIndex < upperBoundIndex && a.get(midIndex + 1) >= x[i]) {
                        sortedIndex = midIndex + 1;
                        break outerLoop;
                    }

                    lowerBoundIndex = midIndex;
                    midIndex = (lowerBoundIndex + upperBoundIndex) / 2;

                    if (lowerBoundIndex == midIndex
                    || upperBoundIndex == midIndex) {

                        if (a.get(upperBoundIndex) <= x[i]) {
                            sortedIndex = a.size();
                            break outerLoop;
                        }
                        else {
                            sortedIndex = upperBoundIndex - 1;
                            break outerLoop;

                        }
                    }
                }
                // if the value is equal to midIndex value
                else {

                    sortedIndex = midIndex;
                    break outerLoop;
                }
            }

            // add the value at appropriate index
            a.add(sortedIndex, x[i]);

        }

        // replace input Array values by ArrayList values
        for (int i = 0; i < x.length; i++) {

            x[i] = a.get(i);

        }
    }

    ///////////////////////////////////////////////////////////////////////////

    // Insertion Sort:

    public static void sort(double[] x) {

        for (int k = 0; k < x.length - 1; k++) {

            // Index of the last number that has been sorted so far
            int iSorted = k;

            // The next number in the array (hasn't been sorted yet)
            double tmp = x[iSorted + 1];

            // Shift elements to the right until we find where tmp belongs
            while (iSorted >= 0 && tmp < x[iSorted]) {
                x[iSorted + 1] = x[iSorted];
                iSorted = iSorted - 1;
            }

            // Place tmp in its correct place
            x[iSorted + 1] = tmp;

        }
    }

    ///////////////////////////////////////////////////////////////////////////

}
