import java.util.Arrays;

public class LargestNumber {
    public static void main(String[] args) {
        LargestNumber solution = new LargestNumber();
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public String largestNumber(int[] nums) {
        Integer[] sorted = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
        }
        Arrays.sort(sorted, (a, b) -> {
            long ab = concatenate(a, b);
            long ba = concatenate(b, a);
            return Long.compare(ba, ab);
        });
        if (sorted[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int num : sorted) {
            sb.append(num);
        }
        return sb.toString();
    }

    private static long concatenate(int a, int b) {
        long shifted = a;
        int temp = b;
        while (temp > 0) {
            shifted *= 10;
            temp /= 10;
        }
        return shifted + b;
    }
}
