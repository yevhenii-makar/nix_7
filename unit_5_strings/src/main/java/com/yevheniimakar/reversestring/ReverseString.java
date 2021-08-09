package com.yevheniimakar.reversestring;

public final class ReverseString {

    private ReverseString() { }

    public static String reverse(String str, boolean reversOnlyWord) {
        String demister = "\\p{Z}";
        String result = "";

        if (reversOnlyWord) {
            String[] strArr = str.split(demister);
            for (String s : strArr) {
                result += ReverseString.reverseWord(s) + " ";
            }
            if (result.length() > 2) {
                result = result.substring(0, result.length() - 2);
            }
        } else {
            result = ReverseString.reverseWord(str);
        }
        return result;
    }

    public static String reverse(String str, String substring, boolean reverseAll) {
        String result;
        if (str.contains(substring)) {
            int startIndex = str.indexOf(substring);
            char[] reversedSubString = ReverseString.reverseWord(substring).toCharArray();
            char[] chars = str.toCharArray();
            for (int x = 0; x < reversedSubString.length; x++) {
                chars[startIndex + x] = reversedSubString[x];
            }
            result = new String(chars);
            if (result.contains(substring) && reverseAll) {
                result = reverse(result, substring, reverseAll);
            }
        } else {
            result = str;
        }
        return result;
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        String result = null;
        if (firstIndex == 0 && firstIndex < lastIndex && lastIndex < str.length()) {
            char[] reverse = ReverseString.reverseWord(str.substring(firstIndex, lastIndex)).toCharArray();
            char[] charsResult = str.toCharArray();
            for (int x = 0; x < reverse.length; x++) {
                charsResult[firstIndex + x] = reverse[x];
            }
            result = new String(charsResult);
        }
        return result != null ? result : str;
    }

    public static String reverse(String str, char firstChar, char lastChar) {
        int firstIndex = str.indexOf(firstChar);
        int lastIndex = str.lastIndexOf(lastChar, firstIndex);
        return (firstIndex == -1 || lastIndex == -1) ? str : ReverseString.reverse(str, firstIndex, lastIndex);
    }

    public static String reverse(String str, String firstSubString, String lastSubString) {
        int firstIndex = str.indexOf(firstSubString);
        int lastIndex = str.lastIndexOf(lastSubString, firstIndex) + lastSubString.length();
        return (firstIndex == -1 || lastIndex == -1) ? str : ReverseString.reverse(str, firstIndex, lastIndex);
    }

    private static String reverseWord(String str) {
        char[] chars = str.toCharArray();
        char[] reverse = new char[chars.length];

        for (int x = 0; x < chars.length; x++) {
            reverse[x] = chars[chars.length - 1 - x];
        }
        return chars.length > 0 ? new String(reverse) : "";
    }
}
