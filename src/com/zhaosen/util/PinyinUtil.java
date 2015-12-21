//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    private HanyuPinyinOutputFormat format = null;
    private String[] pinyin;

    public PinyinUtil() {
        this.format = new HanyuPinyinOutputFormat();
        this.format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        this.format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        this.format.setVCharType(HanyuPinyinVCharType.WITH_V);
        this.pinyin = null;
    }

    public String getFirstPinYin(String str) {
        if(str == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            String tempPinyin = null;

            for(int i = 0; i < str.length(); ++i) {
                tempPinyin = this.getFirstCharacterPinYin(str.charAt(i));
                if(tempPinyin == null) {
                    sb.append(str.charAt(i));
                } else {
                    sb.append(tempPinyin);
                }
            }

            return sb.toString();
        }
    }

    public String getPinYin(String str) {
        if(str == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            String tempPinyin = null;

            for(int i = 0; i < str.length(); ++i) {
                tempPinyin = this.getCharacterPinYin(str.charAt(i));
                if(tempPinyin == null) {
                    sb.append(str.charAt(i));
                } else {
                    sb.append(tempPinyin);
                }
            }

            return sb.toString();
        }
    }

    public String getFirstCharacterPinYin(char c) {
        try {
            this.pinyin = PinyinHelper.toHanyuPinyinStringArray(c, this.format);
        } catch (BadHanyuPinyinOutputFormatCombination var3) {
            var3.printStackTrace();
        }

        if(this.pinyin == null) {
            return null;
        } else {
            String py = this.pinyin[0];
            py = py.substring(0, 1);
            return py;
        }
    }

    public String getCharacterPinYin(char c) {
        try {
            this.pinyin = PinyinHelper.toHanyuPinyinStringArray(c, this.format);
        } catch (BadHanyuPinyinOutputFormatCombination var3) {
            var3.printStackTrace();
        }

        return this.pinyin == null?null:this.pinyin[0];
    }
}
