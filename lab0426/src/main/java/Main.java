public class Main {
    public static void main(String[] args) {
        int[] x = {4, 0, 44, 1, 8, 5};
        for (int i = 0; i < x.length; i++) {
            int min = i;
            for (int j = min; j < x.length; j++) {
                if(x[min] > x[j]) {
                    min = j;
                }
            }
            int help = x[i];
            x[i] = x[min];
            x[min] = help;
        }
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
