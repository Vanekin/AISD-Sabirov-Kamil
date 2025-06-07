import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        int[] array = {1, 2, 1, 4, 5, 6, 55, 7, 64};
        int t = 65;
        int[] result = findClosestTriplet(array, t);
        System.out.println(Arrays.toString(result));
    }
    public static int[] findClosestTriplet(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        int[] result = new int[]{nums[0], nums[1], nums[2]};
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum == target) {
                    return new int[]{nums[i], nums[left], nums[right]};
                }
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                    result = new int[]{nums[i], nums[left], nums[right]};
                }
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}