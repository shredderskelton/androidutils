package com.shredder.utils.crypto;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class CryptoBase64 {
    public static String base64Encode(String text) {
        byte[] data = null;
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String base64Decode(String text) {
        // Receiving side
        byte[] data = Base64.decode(text, Base64.DEFAULT);
        String base64DecodedString = null;
        try {
            base64DecodedString = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64DecodedString;
    }

}
