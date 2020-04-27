package com.yhf.xuedaoqian.util;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/17 19:15
 */
public class ToolUtil {

    private static final char UNDERLINE = '_';

    public static String getUUid() {
        java.util.UUID uuid = com.fasterxml.uuid.Generators.randomBasedGenerator().generate();
        return uuid.toString();
    }

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
