package Deque;

public class MainDeque {
    public static void main(String[] args) {
        String stroka = "(p{}])";
        String str1 = "()[]][";
        System.out.println(cheсk(str1));
        System.out.println(cheсk(stroka));
    }

    public static boolean cheсk(String s) {
        Stack<Character> stack = new Stack();
        if (!((s.length() % 2) == 0)) return false;
        if (s.charAt(0) == '(' || s.charAt(0) == '{' || s.charAt(0) == '[') {
            stack.pull(s.charAt(0));
        } else return false;
        for (int i = 1; i < s.length(); i++) {
            if (stack.isEmpty()) {
                if (s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == ')') return false;
                stack.pull(s.charAt(i));
                continue;
            }
            if (stack.peek() == '(') {
                if (s.charAt(i) == ')') {
                    stack.pop();
                    continue;
                }
                if (s.charAt(i) == '}' || s.charAt(i) == ']') return false;
                if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                    stack.pull(s.charAt(i));
                    continue;
                }
            }
            if (stack.peek() == '{') {
                if (s.charAt(i) == '}') {
                    stack.pop();
                    continue;
                }
                if (s.charAt(i) == ')' || s.charAt(i) == ']') return false;
                if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                    stack.pull(s.charAt(i));
                    continue;
                }
            }
            if (stack.peek() == '[') {
                if (s.charAt(i) == ']') {
                    stack.pop();
                    continue;
                }
                if (s.charAt(i) == '}' || s.charAt(i) == ')') return false;
                if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                    stack.pull(s.charAt(i));
                }
            }
        }
        if (stack.isEmpty()) return true;
        else return false;
    }
}
