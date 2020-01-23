package com.rainbow.common.util;

import java.util.UUID;

public class UUIDUtils {

    public static String getLowerCaseUUID() {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .toLowerCase();
    }


    public static String getUpperCaseUUID() {
        return UUID.randomUUID().toString()
                .replaceAll("-", "")
                .toUpperCase();
    }


}
