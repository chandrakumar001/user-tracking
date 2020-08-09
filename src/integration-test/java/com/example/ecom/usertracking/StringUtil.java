package com.example.ecom.usertracking;

public class StringUtil {
    public static String convertToId(String text) {
        System.out.println(text);
        String s = text.split("/v1/hardware/")[1];
        return s;
    }
}