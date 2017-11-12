package com.lj.utils;

import java.util.UUID;

/**
 * Created by lj0782 on 2017/11/2.
 */
public class KeyUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
