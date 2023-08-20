public class exercicio14{
    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Base case: array already sorted or empty
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Split the array into two halves
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);

        // Recursively sort each half
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        mergeSort(array);

        System.out.println("Array ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}