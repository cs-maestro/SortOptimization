import java.util.*;

public class ArrayListSorting {

    ///////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        var doubles = new ArrayList<Double>();

        // input doubles into an ArrayList at sorted positions
        for (int i = 0; i < 1000; i++) {

            double tmp = (Math.random() * 10);
            AddNewNumber(doubles, tmp);
        }

    }

    ///////////////////////////////////////////////////////////////////////////

    private static void AddNewNumber(ArrayList<Double> a, double x) {

        // find the sortedIndex (where the input vale fits) in the ArrayList
        // add the number in the sortedIndex
        int sortedIndex = 0;

        int upperBoundIndex = a.size() - 1;
        int lowerBoundIndex = 0;
        int midIndex = a.size() / 2;

        outerLoop: for
        (int i = 0; i <= (Math.log(a.size()) / Math.log(2)); i++) {

            // check if the number fits in left half
            if (a.get(midIndex) > x) {

                if (midIndex > lowerBoundIndex && a.get(midIndex - 1) <= x) {
                    sortedIndex = midIndex;
                    break outerLoop;
                }

                upperBoundIndex = midIndex;
                midIndex = (lowerBoundIndex + upperBoundIndex) / 2;

                if (lowerBoundIndex == midIndex
                || upperBoundIndex == midIndex) {

                    if (a.get(lowerBoundIndex) >= x) {
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
            else if (a.get(midIndex) < x) {

                if (midIndex < upperBoundIndex && a.get(midIndex + 1) >= x) {
                    sortedIndex = midIndex + 1;
                    break outerLoop;
                }

                lowerBoundIndex = midIndex;
                midIndex = (lowerBoundIndex + upperBoundIndex) / 2;

                if (lowerBoundIndex == midIndex
                || upperBoundIndex == midIndex) {

                    if (a.get(upperBoundIndex) <= x) {
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
        a.add(sortedIndex, x);
    }


    ///////////////////////////////////////////////////////////////////////////

}
