import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        int[] nums1 = {2, 4, 4, 1};
        int[] nums2 = {2, 2, 4, 5};
        System.out.println(Arrays.toString(findCommonElements(nums1, nums2))); // [2, 4]
    }

    public static int[] findCommonElements(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq1 = new LinkedHashMap<>();
        for (int num : nums1) {
            freq1.put(num, freq1.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> freq2 = new HashMap<>();
        for (int num : nums2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freq1.entrySet()) {
            int num = entry.getKey();
            if (freq2.containsKey(num)) {
                int minCount = Math.min(entry.getValue(), freq2.get(num));
                for (int i = 0; i < minCount; i++) {
                    result.add(num);
                }
            }
        }
        int[] arr = new int[result.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}