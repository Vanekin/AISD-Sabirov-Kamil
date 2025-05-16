public class Task1 {
    public static void main(String[] args) {
        int[] array = {1, 4,4,4, 4,4,4,7};
        findEl1(array, 4);
        findEl2(array, 4);
    }
    public static void findEl1(int[] array, int a) {
        int left = 0;
        int right = array.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (array[mid] == a) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        System.out.println(mid);

    }
    public static void findEl2(int[] array, int a) {
        int left = 0;
        int right = array.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (array[mid] > a) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }
        System.out.println(mid);

    }
}
