public class Main {
    public static void main(String[] args) {
        int[][] array = {
                { 5,  0,  7, 52, 3,  3},
                { 6, 81,  5, 51, 6,  4},
                { 7,  6,  8, 55, 7, 14},
                {41, 85,  6,  3, 7,  2},
                {87,  6, 65,  9, 5,  3},
                {12,  7,  5,  9, 0,  0},
        };
        int[][] array2 = {
                {1, 3, 2},
                {2, 4, 5},
                {1, 2, 1}
        };
        countShortWay(array);
        countShortWay(array2);

    }

    public static void countShortWay(int[][] array) {
        for (int i = 1; i < array[0].length; i++) {
            array[0][i] = array[0][i] + array[0][i - 1];
        }
        for (int i = 1; i < array.length; i++) {
            array[i][0] = array[i][0] + array[i - 1][0];
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                array[i][j] = array[i][j] + min(array[i][j-1], array[i - 1][j]);
            }
        }
        System.out.println(array[array.length - 1][array[array.length - 1].length - 1]);
    }

    public  static int min(int x1, int x2) {
        if (x1 > x2) return x2;
        return x1;
    }
}
