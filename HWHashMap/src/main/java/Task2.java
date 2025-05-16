import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 5};
        int k = 3;
        System.out.println(containsNearbyDuplicate(nums, k));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> IndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (IndexMap.containsKey(num) && i - IndexMap.get(num) <= k) {
                return true;
            }
            IndexMap.put(num, i);
        }
        return false;
    }
}