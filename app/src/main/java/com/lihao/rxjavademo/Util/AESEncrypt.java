package com.lihao.rxjavademo.Util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt{

    public static String getIPV4Address(){

        return "127.0.0.1";
    }

    private static byte[] a = new byte[]{(byte)18, (byte)52, (byte)86, (byte)120, (byte)-112, (byte)-85, (byte)-51, (byte)-17, (byte)18, (byte)52, (byte)86, (byte)120, (byte)-112, (byte)-85, (byte)-51, (byte)-17};

    private AESEncrypt() {
    }

    public static byte[] AESEncrypt(String var0, String var1) {
        byte[] var2 = null;
        byte[] var12 = var1.getBytes();
        byte[] var11 = var0.getBytes();

        try {
            SecretKeySpec var13 = new SecretKeySpec(var12, 0, var12.length, "AES");
            Cipher var3 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec var4 = new IvParameterSpec(a, 0, a.length);
            var3.init(1, var13, var4);
            var2 = var3.doFinal(var11);
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        } catch (NoSuchPaddingException var6) {
            var6.printStackTrace();
        } catch (BadPaddingException var7) {
            var7.printStackTrace();
        } catch (IllegalBlockSizeException var8) {
            var8.printStackTrace();
        } catch (InvalidKeyException var9) {
            var9.printStackTrace();
        } catch (InvalidAlgorithmParameterException var10) {
            var10.printStackTrace();
        }

        return var2;
    }

    public static byte[] AESDecrypt(byte[] var0, String var1) {
        byte[] var2 = null;
        byte[] var11 = var1.getBytes();

        try {
            SecretKeySpec var12 = new SecretKeySpec(var11, 0, var11.length, "AES");
            Cipher var3 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec var4 = new IvParameterSpec(a, 0, a.length);
            var3.init(2, var12, var4);
            var2 = var3.doFinal(var0);
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        } catch (NoSuchPaddingException var6) {
            var6.printStackTrace();
        } catch (IllegalBlockSizeException var7) {
            var7.printStackTrace();
        } catch (BadPaddingException var8) {
            var8.printStackTrace();
        } catch (InvalidKeyException var9) {
            var9.printStackTrace();
        } catch (InvalidAlgorithmParameterException var10) {
            var10.printStackTrace();
        }

        return var2;
    }

    public static String toHexString(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            String var3;
            if((var3 = Integer.toHexString(var0[var2] & 255)).length() == 1) {
                var3 = "0" + var3;
            }

            var1.append(var3.toUpperCase());
        }

        return var1.toString();
    }
}
