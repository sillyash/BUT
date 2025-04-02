package com.sillyash.but.tp1.Server;

public class ServerTools {
    public static final String URL = "giraudot.com";
    public static final int PORT = 9874;
    public static boolean isTableNumberOK(int n) {
        if (n <= 0 || n >= 100) return false;
        return true;
    }
    public static String numToString(int n) {
        if ( ! isTableNumberOK(n)) return "";
        if (n < 10) return "0" + n;
        return String.valueOf(n);
    }
}
