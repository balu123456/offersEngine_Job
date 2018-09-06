package com.clearlane.offersengine_job.web.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author rubykannan.ramaiah
 *
 */
public class StringUtils {

    private static final String NULL = "null";

    private StringUtils() {
        // This util shouldn't be initialized anywhere
    }

    /**
     * Determines if a string is null or empty. Note: string "null" evaluates to true (is null)
     */
    public static boolean isNullOrEmpty(String input) {
        return (input == null) || (input.trim().length() == 0) || NULL.equalsIgnoreCase(input);
    }

    public static boolean isNotNullOrEmpty(String input) {
        return !isNullOrEmpty(input);
    }

    /**
     * Splits the given String based on the given delimeter and return 
     * a list of strings
     * 
     * @param str
     * @param delimeter
     * @return
     */
    public static List<String> tokenizeList(String str, String delimeter) {
        List<String> strList = new LinkedList<>();

        StringTokenizer strTokens = new StringTokenizer(str, delimeter);
        while (strTokens.hasMoreTokens()) {
            strList.add(strTokens.nextToken().trim());
        }

        return strList;
    }

    /**
     * Concats the given strings. If delimeter is passed then it is appended between strings.
     * @param delimeter
     * @param strList
     * @return
     */
    public static String concat(String delimeter, String... strList) {
        StringBuilder strBuff = new StringBuilder();
        boolean first = true;

        for (String str : strList) {
            if (delimeter != null && delimeter.length() > 0 && !first) {
                strBuff.append(delimeter);
            }
            strBuff.append(str);
            first = false;
        }

        return strBuff.toString();
    }

    /**
     * Concats the given strings. If delimeter is passed then it is appended between strings.
     * @param delimeter
     * @param strList
     * @return
     */
    public static String concat(String delimeter, Collection<String> strList) {
        StringBuilder strBuff = new StringBuilder();
        boolean first = true;

        if (strList != null) {
            for (String str : strList) {
                if (delimeter != null && delimeter.length() > 0 && !first) {
                    strBuff.append(delimeter);
                }
                strBuff.append(str);
                first = false;
            }
        }

        return strBuff.toString();
    }

    /**
    +     * Returns null if the passed string is null or empty else trims and returns the passed string.
    +     * 
    +     * @param str
    +     * @return
    +     */
    public static String trimStringIfNotNull(String str) {
        if ((str == null) || (str.trim().length() == 0)) {
            return null;
        }
        return str.trim();
    }

    /**
     * Returns empty string if the passed value is null else trims and returns it.
     * @param strValue
     * @return
     */
    public static String checkNull(String strValue) {
        return strValue == null ? "" : strValue.trim();
    }

    public static String checkLength(String str, Integer maxLength) {
        if (str != null && str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }

    public static String replaceSpecialChars(String origString, String allowedSpecialChars) {
        StringBuilder validatedStr = new StringBuilder();
        for (int i = 0; i < origString.length(); i++) {
            String subOrigString = origString.substring(i, i + 1);
            if (org.apache.commons.lang.StringUtils.isAlphanumeric(subOrigString)
                    || allowedSpecialChars.indexOf(subOrigString) >= 0) {
                validatedStr.append(subOrigString);
            }
            else {
                validatedStr.append(" ");
            }
        }

        return validatedStr.toString();
    }

    public static String toCamelCase(String str) {
        if (str == null || str.trim().length() == 0) {
            return str;
        }
        else if (str.length() == 1) {
            return str.toUpperCase();
        }
        else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    /**
     * Checks if the given raw string contains valid ASCII characters only, return the raw string if all are 
     * valid ASCII characters else returns the given alternate string value.
     */
    public static String checkAsciiPrintable(String rawString, String alternateString) {
        return org.apache.commons.lang.StringUtils.isAsciiPrintable(rawString) ? rawString
                : alternateString;
    }

    /** 
     * Checks if the given string is a valid double value
     */
    public static Boolean isDouble(String dblString) {
        return org.apache.commons.lang.StringUtils.containsOnly(dblString, "0123456789.-");
    }

    /**
     * Checks if the given string is a valid integer value
     */
    public static Boolean isInteger(String intString) {
        return org.apache.commons.lang.StringUtils.containsOnly(intString, "0123456789-");
    }

    /**
     * Removes all non numeric characters from the given string. Keeps the period symbol if the second parameter is passed as true.
     */
    public static String replaceNonNumericChars(String origString, Boolean keepDecimalPoint) {
        return origString.replaceAll(keepDecimalPoint ? "[^0-9.-]" : "[^0-9-]", "");
    }

    public static boolean isEmptyOrNullString(String str) {
        return org.apache.commons.lang.StringUtils.isEmpty(str) || "null".equals(str);
    }

    public static String escapeHtml(String input) {
        if (input == null)
            return null;
        return StringEscapeUtils.escapeHtml(input).replace("\'", " &#x27;").replace("/", "&#x2F;");
    }
    
    /**
     * Utility method to check strings equality in safe manner 
     * and to avoid explicit null checks
     *  
     * return true if both strings are equal(or null)
     *  
     * @param str1
     * @param str2
     * @return boolean
     */
    public static boolean equals(String str1, String str2) {
    	return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }
    /**
     * Utility method to check strings equality(case insensitive) in safe manner 
     * and to avoid explicit null checks
     *  
     * @param str1
     * @param str2
     * @return true if both strings are equal (by ignoring case) or if both inputs are null
     */  
    public static boolean equalsIgnoreCase(String str1, String str2) {
    	return org.apache.commons.lang.StringUtils.equalsIgnoreCase(str1, str2);
    }

}
