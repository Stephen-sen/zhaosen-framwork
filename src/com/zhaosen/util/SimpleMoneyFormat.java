//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

import java.text.NumberFormat;
import java.util.HashMap;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SimpleMoneyFormat {
    public static final String EMPTY = "";
    public static final String ZERO = "零";
    public static final String ONE = "壹";
    public static final String TWO = "贰";
    public static final String THREE = "叁";
    public static final String FOUR = "肆";
    public static final String FIVE = "伍";
    public static final String SIX = "陆";
    public static final String SEVEN = "柒";
    public static final String EIGHT = "捌";
    public static final String NINE = "玖";
    public static final String TEN = "拾";
    public static final String HUNDRED = "佰";
    public static final String THOUSAND = "仟";
    public static final String TEN_THOUSAND = "万";
    public static final String HUNDRED_MILLION = "亿";
    public static final String YUAN = "元";
    public static final String JIAO = "角";
    public static final String FEN = "分";
    public static final String DOT = ".";
    private static SimpleMoneyFormat formatter = null;
    private HashMap chineseNumberMap = new HashMap();
    private HashMap chineseMoneyPattern = new HashMap();
    private NumberFormat numberFormat = NumberFormat.getInstance();

    private SimpleMoneyFormat() {
        this.numberFormat.setMaximumFractionDigits(4);
        this.numberFormat.setMinimumFractionDigits(2);
        this.numberFormat.setGroupingUsed(false);
        this.chineseNumberMap.put("0", "零");
        this.chineseNumberMap.put("1", "壹");
        this.chineseNumberMap.put("2", "贰");
        this.chineseNumberMap.put("3", "叁");
        this.chineseNumberMap.put("4", "肆");
        this.chineseNumberMap.put("5", "伍");
        this.chineseNumberMap.put("6", "陆");
        this.chineseNumberMap.put("7", "柒");
        this.chineseNumberMap.put("8", "捌");
        this.chineseNumberMap.put("9", "玖");
        this.chineseNumberMap.put(".", ".");
        this.chineseMoneyPattern.put("1", "拾");
        this.chineseMoneyPattern.put("2", "佰");
        this.chineseMoneyPattern.put("3", "仟");
        this.chineseMoneyPattern.put("4", "万");
        this.chineseMoneyPattern.put("5", "拾");
        this.chineseMoneyPattern.put("6", "佰");
        this.chineseMoneyPattern.put("7", "仟");
        this.chineseMoneyPattern.put("8", "亿");
    }

    public static SimpleMoneyFormat getInstance() {
        if(formatter == null) {
            formatter = new SimpleMoneyFormat();
        }

        return formatter;
    }

    public String format(String moneyStr) {
        this.checkPrecision(moneyStr);
        String result = this.convertToChineseNumber(moneyStr);
        result = this.addUnitsToChineseMoneyString(result);
        return result;
    }

    public String format(double moneyDouble) {
        return this.format(this.numberFormat.format(moneyDouble));
    }

    public String format(int moneyInt) {
        return this.format(this.numberFormat.format((long)moneyInt));
    }

    public String format(long moneyLong) {
        return this.format(this.numberFormat.format(moneyLong));
    }

    public String format(Number moneyNum) {
        return this.format(this.numberFormat.format(moneyNum));
    }

    private String convertToChineseNumber(String moneyStr) {
        StringBuffer cMoneyStringBuffer = new StringBuffer();

        int indexOfDot;
        for(indexOfDot = 0; indexOfDot < moneyStr.length(); ++indexOfDot) {
            cMoneyStringBuffer.append(this.chineseNumberMap.get(moneyStr.substring(indexOfDot, indexOfDot + 1)));
        }

        indexOfDot = cMoneyStringBuffer.indexOf(".");
        int moneyPatternCursor = 1;

        for(int var7 = indexOfDot - 1; var7 > 0; --var7) {
            cMoneyStringBuffer.insert(var7, this.chineseMoneyPattern.get("" + moneyPatternCursor));
            moneyPatternCursor = moneyPatternCursor == 8?1:moneyPatternCursor + 1;
        }

        String var71 = cMoneyStringBuffer.substring(cMoneyStringBuffer.indexOf("."));
        cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("."), cMoneyStringBuffer.length());

        while(cMoneyStringBuffer.indexOf("零拾") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零拾"), cMoneyStringBuffer.indexOf("零拾") + 2, "零");
        }

        while(cMoneyStringBuffer.indexOf("零佰") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零佰"), cMoneyStringBuffer.indexOf("零佰") + 2, "零");
        }

        while(cMoneyStringBuffer.indexOf("零仟") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零仟"), cMoneyStringBuffer.indexOf("零仟") + 2, "零");
        }

        while(cMoneyStringBuffer.indexOf("零万") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零万"), cMoneyStringBuffer.indexOf("零万") + 2, "万");
        }

        while(cMoneyStringBuffer.indexOf("零亿") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零亿"), cMoneyStringBuffer.indexOf("零亿") + 2, "亿");
        }

        while(cMoneyStringBuffer.indexOf("零零") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零零"), cMoneyStringBuffer.indexOf("零零") + 2, "零");
        }

        if(cMoneyStringBuffer.lastIndexOf("零") == cMoneyStringBuffer.length() - 1) {
            cMoneyStringBuffer.delete(cMoneyStringBuffer.length() - 1, cMoneyStringBuffer.length());
        }

        cMoneyStringBuffer.append(var71);
        String result = cMoneyStringBuffer.toString();
        return result;
    }

    private String addUnitsToChineseMoneyString(String moneyStr) {
        StringBuffer cMoneyStringBuffer = new StringBuffer(moneyStr);
        int indexOfDot = cMoneyStringBuffer.indexOf(".");
        cMoneyStringBuffer.replace(indexOfDot, indexOfDot + 1, "元");
        cMoneyStringBuffer.insert(cMoneyStringBuffer.length() - 1, "角");
        cMoneyStringBuffer.insert(cMoneyStringBuffer.length(), "分");
        if(cMoneyStringBuffer.indexOf("零角零分") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零角零分"), cMoneyStringBuffer.length(), "整");
        } else if(cMoneyStringBuffer.indexOf("零分") != -1) {
            cMoneyStringBuffer.replace(cMoneyStringBuffer.indexOf("零分"), cMoneyStringBuffer.length(), "整");
            if(cMoneyStringBuffer.indexOf("零角") != -1) {
                cMoneyStringBuffer.delete(cMoneyStringBuffer.indexOf("零角"), cMoneyStringBuffer.indexOf("零角") + 2);
            }
        }

        String result = cMoneyStringBuffer.toString();
        return result;
    }

    private void checkPrecision(String moneyStr) {
        int fractionDigits = moneyStr.length() - moneyStr.indexOf(".") - 1;
        if(fractionDigits > 2) {
            throw new RuntimeException("金额" + moneyStr + "的小数位多于两位。");
        }
    }

    public static void main(String[] args) {
        System.out.println(getInstance().format((Number)(new Double(0.1D))));
    }
}
