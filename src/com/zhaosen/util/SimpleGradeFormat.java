//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

public class SimpleGradeFormat {
    public SimpleGradeFormat() {
    }

    public static String getGradeStr(String num) {
        return num.equals("1")?"一":(num.equals("2")?"二":(num.equals("3")?"三":(num.equals("4")?"四":(num.equals("5")?"五":(num.equals("6")?"六":(num.equals("7")?"七":(num.equals("8")?"八":(num.equals("9")?"九":(num.equals("11")?"高一":(num.equals("12")?"高二":(num.equals("13")?"高三":"")))))))))));
    }
}
