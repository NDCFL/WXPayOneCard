package com.cfl.comment.util;

/**
 * Created by Wang Genshen on 2017-07-14.
 */
public class PhoneUtil {
    public static String hidePhone(String phone) {
        if (phone == null || phone.length() < 11) {
            return "";
        }
        return phone.substring(0,3) + "****" + phone.substring(7);
    }
}
