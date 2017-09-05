package com.hy.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by WangShuai on 2016/8/18.
 */
public class NumberUtils {

    public static double doubleHalfUp(double d,int scale){
        if(scale<0){
            scale = 2;
        }
        BigDecimal bg = new BigDecimal(d).setScale(scale, RoundingMode.HALF_UP);

        return bg.doubleValue();
    }

    public static double double2HalfUp(double d){
        return doubleHalfUp(d,2);
    }
}
