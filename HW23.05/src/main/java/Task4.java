import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        int[] nums = {15, 5, 11, 10, 12};
        int s = 30;
        System.out.println(maxNumbersWithinSum(nums, s));
    }

    public static int maxNumbersWithinSum(int[] nums, int s) {
        Arrays.sort(nums);
        int count = 0;
        int currentSum = 0;
        for (int num : nums) {
            if (currentSum + num <= s) {
                currentSum += num;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}