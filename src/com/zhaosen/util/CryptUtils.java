package com.zhaosen.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CryptUtils {

    public CryptUtils() {
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom var7 = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey, var7);
            return cipher.doFinal(src);
        } catch (Exception var71) {
            throw new RuntimeException(var71);
        }
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws RuntimeException {
        try {
            SecureRandom var7 = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, securekey, var7);
            return cipher.doFinal(src);
        } catch (Exception var71) {
            throw new RuntimeException(var71);
        }
    }

    public static final String decrypt(String data, String key) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
    }

    public static final String encrypt(String data, String key) {
        if(data != null) {
            try {
                return byte2hex(encrypt(data.getBytes(), key.getBytes()));
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        } else {
            return null;
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();

        for(int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if(stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if(b.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            byte[] b2 = new byte[b.length / 2];

            for(int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }

    public static void main(String[] args) throws Exception {
        String str = "中国移动电信{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}{id:1, pId:0, name: \"菜单\",menuType:\"01\"}";
        String h = encrypt(str, "20130304");
        System.out.println(h);
        String l = decrypt(h, "20130304");
        System.out.println(l);
    }
}
