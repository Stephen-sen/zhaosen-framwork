
package com.zhaosen.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommonUtil {
    public CommonUtil() {
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static String getRandomString(int length) {
        if(length <= 0) {
            return "";
        } else {
            char[] randomChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
            Random random = new Random();
            StringBuffer stringBuffer = new StringBuffer();

            for(int i = 0; i < length; ++i) {
                stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
            }

            return stringBuffer.toString();
        }
    }

	public static List<String> splitString(String str, int length) {
        ArrayList list = new ArrayList();

        for(int i = 0; i < str.length(); i += length) {
            int endIndex = i + length;
            if(endIndex <= str.length()) {
                list.add(str.substring(i, i + length));
            } else {
                list.add(str.substring(i, str.length() - 1));
            }
        }

        return list;
    }

    public static String toString(List<String> list, String separator) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            String str = (String)var4.next();
            stringBuffer.append(separator + str);
        }

        stringBuffer.deleteCharAt(0);
        return stringBuffer.toString();
    }

    public static String formatNum(Double value) {
        String retValue = null;
        if(value != null) {
            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(0);
            df.setMaximumFractionDigits(1);
            retValue = df.format(value);
        }

        return retValue;
    }
}
