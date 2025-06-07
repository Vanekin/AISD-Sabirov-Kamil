import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, List<Integer>> moves = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int k = scanner.nextInt();
            List<Integer> options = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                options.add(scanner.nextInt());
            }
            moves.put(i, options);
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        for (int i = 1; i <= n; i++) {
            boolean isWinning = false;
            for (int move : moves.get(i)) {
                if (i - move >= 0 && !dp[i - move]) {
                    isWinning = true;
                    break;
                }
            }
            dp[i] = isWinning;
        }
        System.out.println(dp[n] ? "First" : "Second");
    }
}