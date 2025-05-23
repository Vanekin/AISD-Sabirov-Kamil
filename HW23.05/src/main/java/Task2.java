public class Task2 {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("this is ib"));
    }

        public static String reverseWords(String s) {
            StringBuilder result = new StringBuilder();
            int length = s.length();
            int i = length - 1;
            while (i >= 0) {
                while (i >= 0 && s.charAt(i) == ' ') {
                    i--;
                }
                if (i < 0) break;
                int end = i;
                while (i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                for (int j = i + 1; j <= end; j++) {
                    result.append(s.charAt(j));
                }
                if (i >= 0) {
                    result.append(' ');
                }
            }
            if (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
                result.deleteCharAt(result.length() - 1);
            }
            return result.toString();
        }
}
