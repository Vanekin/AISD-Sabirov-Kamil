public class Main {
    public static void main(String[] args) {
        System.out.println(karatcuba("10011011","10111010"));
    }

    public static Integer karatcuba(String s1, String s2) {
        if (s1.length() == 1 && s2.length() == 1) {
            int n1 = s1.charAt(0) - '0';
            int n2 = s2.charAt(0) - '0';
            return Integer.valueOf(n1 * n2);
        }
        String a = s1.substring(0,s1.length()/2);
        String b = s1.substring(s1.length()/2);
        double x = Math.pow(2,s1.length()/2);
        String c = s2.substring(0,s2.length()/2);
        String d = s2.substring(s2.length()/2,s2.length());
        return (int) (karatcuba(a,c)*x*x+karatcuba(b,d)+((karatcuba(b,c)+karatcuba(a,d))*x));
    }
}

