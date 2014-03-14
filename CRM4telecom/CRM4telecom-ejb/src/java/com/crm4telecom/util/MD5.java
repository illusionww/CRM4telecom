package com.crm4telecom.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MD5 {

    public static String getHash(String password, String salt) {
        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            Date d = new Date();
            md5.reset();
            String str = salt + password;
            md5.update(str.getBytes());
            byte messageDigest[] = md5.digest();

            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }

    public static List<String> getSaltPassword(String password, String login) throws NoSuchAlgorithmException {

        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();

        List<String> l = new ArrayList();
        md5 = MessageDigest.getInstance("md5");
        Date d = new Date();

        md5.reset();
        String salt = Long.toString(d.getTime()) + "!SD@#$D@XC" + password;
        StringBuffer salthash = new StringBuffer();
        md5.update(salt.getBytes());
        byte saltDigest[] = md5.digest();

        for (int i = 0; i < saltDigest.length; i++) {
            salthash.append(Integer.toHexString(0xFF & saltDigest[i]));
        }
        salt = salthash.toString();
        l.add(salthash.toString());
        System.out.println("salt == " + salt);
        String ss = salt + password;
        md5.reset();

        md5.update(ss.getBytes());

        byte messageDigest[] = md5.digest();

        for (int i = 0; i < messageDigest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        }
        l.add(hexString.toString());

        return l;
    }

}
