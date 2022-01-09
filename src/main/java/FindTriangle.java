public class FindTriangle {
    public static void main(String[] args) {
        SDATA[] coordinates = new SDATA[4];
        System.out.println("Before sorting...\n");
        System.out.println("[");
        for (int i = 0; i < coordinates.length; i++) {
            int x = (int)Math.floor(Math.random()*(8-1+1)+1);
            int y = (int)Math.floor(Math.random()*(8-1+1)+1);
            coordinates[i] = new SDATA(x, y);
            System.out.println("("+ coordinates[i].infoX + ", " + coordinates[i].infoY + ")");
        }
        System.out.println("]\n");


        SDATA[] smallest = runAlgorithm(coordinates);
        System.out.println("After Algorithm...\n");
        System.out.println("[");
        for (int i = 0; i < smallest.length; i++) {
            System.out.println("("+ smallest[i].infoX + ", " + smallest[i].infoY + ")");
        }
        System.out.println("]\n");
    }
    public static SDATA[] runAlgorithm(SDATA[] A){
        System.out.println("After sorting...\n");
        sort(A, 0, A.length-1);
        System.out.println("[");
        for (int i = 0; i < A.length; i++) {
            System.out.println("("+ A[i].infoX + ", " + A[i].infoY + ")");
        }
        System.out.println("]\n");
        SDATA[] smallest = new SDATA[3];
        double sideOne = 0;
        double sideTwo = 0;
        double sideThree = 0;
        double perimeter = 0;
        double previousPerimeter = 0;
        double smallestPerimeter = 0;

        for (int i = 0; i < A.length-2; i++) {
            sideOne = Math.sqrt(Math.pow(Math.abs(A[i].infoX - A[i+1].infoX), 2) + Math.pow(Math.abs(A[i].infoY - A[i+1].infoY), 2));
            sideTwo = Math.sqrt(Math.pow(Math.abs(A[i].infoX - A[i+2].infoX), 2) + Math.pow(Math.abs(A[i].infoY - A[i+2].infoY), 2));
            sideThree = Math.sqrt(Math.pow(Math.abs(A[i+1].infoX - A[i+2].infoX), 2) + Math.pow(Math.abs(A[i+1].infoY - A[i+2].infoY), 2));
            perimeter = sideOne + sideTwo + sideThree;
            if (perimeter < previousPerimeter){
                smallestPerimeter = perimeter;
                smallest[0] = A[i];
                smallest[1] = A[i+1];
                smallest[2] = A[i+2];
            }
            if (smallestPerimeter == 0){
                smallestPerimeter = perimeter;
                smallest[0] = A[i];
                smallest[1] = A[i+1];
                smallest[2] = A[i+2];
            }
            previousPerimeter = perimeter;
        }

        return smallest;
    }
    public static void merge(SDATA arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        SDATA L[] = new SDATA[n1];
        SDATA R[] = new SDATA[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].infoX + L[i].infoY <= R[j].infoX + R[j].infoY) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(SDATA arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}

