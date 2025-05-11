public class Log {
    public static void main(String[] args) {
        System.out.println(countLog(2));
    }

    public static double countLog(int n) {
        if (n == 1) return 0;
        return (countLog(n - 1) + Math.log(n));
    }
}

