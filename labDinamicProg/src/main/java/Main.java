public class Main {
    public static void main(String[] args) {
        fib(54);
    }

    public static void fib(int a) {
        boolean flag = false;
        int x1 = 1;
        int x2 = 1;
        int fib = x1 + x2;
        while (fib < a) {
            x1 = x2;
            fib = x2 + x1;
            System.out.println(fib);
        }
        if (a == fib) {
            flag = true;
        }
        System.out.println(flag);
    }
}
