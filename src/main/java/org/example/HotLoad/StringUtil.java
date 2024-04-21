package org.example.HotLoad;

public  class StringUtil {
    public static void main(String[] args) {
        String input = "example.com.";
        String result = removeLastDot(input);
        System.out.println(result);
        String substring = input.substring(2);
        System.out.println(substring);
    }
    public static String removeLastDot(String input) {
        if (input != null && input.length() > 0 && input.charAt(input.length() - 1) == '.') {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }
}
