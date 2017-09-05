package com.hy.common.utils;

import com.hy.exception.ExceptionUtil;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by WangShuai on 2016/9/18.
 */
public class BigDecimalUtils {

    public static final int MIN_SCALE = 0;
    public static final int MAX_SCALE = 10;

    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final String COMPARE_TYPE_GREATER = "1";
    public static final String COMPARE_TYPE_GREATER_OR_EQUALS = "1,0";
    public static final String COMPARE_TYPE_EQUALS = "0";
    public static final String COMPARE_TYPE_LESS = "-1";
    public static final String COMPARE_TYPE_LESS_OR_EQUALS = "-1,0";

    /*构造BigDecimal*/
    public static BigDecimal constructBigDecimal(Integer d){
        return new BigDecimal(Integer.toString(d));
    }
    public static BigDecimal constructBigDecimal(Double d){
        return new BigDecimal(Double.toString(d));
    }
    public static BigDecimal constructBigDecimal(String num){
        return new BigDecimal(num);
    }

    /*加法*/
    public static BigDecimal add(BigDecimal bd1,BigDecimal bd2){
        return bd1.add(bd2);
    }
    public static BigDecimal add(Object n1,Object n2){
        BigDecimal bd1 = constructBigDecimal(String.valueOf(n1));
        BigDecimal bd2 = constructBigDecimal(String.valueOf(n2));
        return add(bd1, bd2);
    }

    /*减法*/
    public static BigDecimal subtract(BigDecimal bd1,BigDecimal bd2){
        return bd1.subtract(bd2);
    }
    public static BigDecimal subtract(Object n1,Object n2){
        BigDecimal bd1 = constructBigDecimal(String.valueOf(n1));
        BigDecimal bd2 = constructBigDecimal(String.valueOf(n2));
        return subtract(bd1, bd2);
    }

    /*乘法*/
    public static BigDecimal multiply(BigDecimal bd1,BigDecimal bd2){
        return bd1.multiply(bd2);
    }
    public static BigDecimal multiply(Object n1,Object n2){
        BigDecimal bd1 = constructBigDecimal(String.valueOf(n1));
        BigDecimal bd2 = constructBigDecimal(String.valueOf(n2));
        return multiply(bd1,bd2);
    }
    public static BigDecimal multiply(BigDecimal bd1,Object n){
        BigDecimal bd2 = constructBigDecimal(String.valueOf(n));
        return multiply(bd1,bd2);
    }

    /*除法*/
    public static BigDecimal divide(BigDecimal bd1,BigDecimal bd2,int scale){
        if(scale<MIN_SCALE){
            scale = MIN_SCALE;
        }
        return bd1.divide(bd2,scale,BigDecimal.ROUND_CEILING);
    }
    public static BigDecimal divide(BigDecimal bd1,BigDecimal bd2,int scale,int roundType){
        if(scale<MIN_SCALE){
            scale = MIN_SCALE;
        }
        return bd1.divide(bd2,scale,roundType);
    }
    public static BigDecimal divide(Object n1,Object n2,int scale){
        if(scale<MIN_SCALE){
            scale = MIN_SCALE;
        }
        BigDecimal bd1 = constructBigDecimal(String.valueOf(n1));
        BigDecimal bd2 = constructBigDecimal(String.valueOf(n2));
        return bd1.divide(bd2,scale,BigDecimal.ROUND_CEILING);
    }
    public static BigDecimal divide(BigDecimal bd1,BigDecimal bd2){
        return divide(bd1,bd2,MIN_SCALE);
    }


    /*比较*/
    public static Boolean compare(BigDecimal bd1, BigDecimal bd2, String compareType){
        compareType = compareType==null ? COMPARE_TYPE_EQUALS : compareType;
        return Arrays.asList(compareType.split(",")).contains(bd1.compareTo(bd2) + "");
    }
    public static Boolean compareWithZero(BigDecimal bd1,String compareType){
        compareType = compareType==null ? COMPARE_TYPE_EQUALS : compareType;
        return Arrays.asList(compareType.split(",")).contains(bd1.compareTo(BigDecimal.ZERO) + "");
    }
    public static Boolean equal(BigDecimal bd1,BigDecimal bd2){
        return compare(bd1, bd2, null);
    }
    public static Boolean greater(BigDecimal bd1,BigDecimal bd2){
        return compare(bd1,bd2,COMPARE_TYPE_GREATER);
    }
    public static Boolean greaterZero(BigDecimal bd1){
        return compare(bd1,ZERO,COMPARE_TYPE_GREATER);
    }
    public static Boolean greaterOrEquals(BigDecimal bd1,BigDecimal bd2){
        return compare(bd1,bd2,COMPARE_TYPE_GREATER_OR_EQUALS);
    }

    public static Boolean less(BigDecimal bd1,BigDecimal bd2){
        return compare(bd1,bd2,COMPARE_TYPE_LESS);
    }
    public static Boolean lessZero(BigDecimal bd1){
        return compare(bd1,ZERO,COMPARE_TYPE_LESS);
    }
    public static Boolean lessOrEquals(BigDecimal bd1,BigDecimal bd2){
        return compare(bd1,bd2,COMPARE_TYPE_LESS_OR_EQUALS);
    }


    /*约值*/
    public static BigDecimal round(BigDecimal bd,int scale,int roundType){
        return bd.setScale(defaultScale(scale),roundType);
    }

    /**
     * 向上取值
     * @param bd
     * @param scale
     * @return
     */
    public static BigDecimal roundCeiling(BigDecimal bd,int scale){
        return bd.setScale(defaultScale(scale),BigDecimal.ROUND_CEILING);
    }

    /**
     * 四舍五入
     * @param bd
     * @param scale
     * @return
     */
    public static BigDecimal roundHalfUp(BigDecimal bd,int scale){
        return bd.setScale(defaultScale(scale),BigDecimal.ROUND_HALF_UP);
    }

    /**
     * scale最小为0
     * @param scale
     * @return
     */
    public static int defaultScale(int scale){
        if(scale<0){
            return 0;
        }
        return scale;
    }

    /**
     * 如果值为null则为0
     * @param bd
     * @return
     */
    public static BigDecimal filterNull(BigDecimal bd){
        return bd==null ? BigDecimal.ZERO : bd;
    }

    /**
     * 设置最小值
     * @param bd
     * @param minValue
     * @return
     */
    public static BigDecimal minValue(BigDecimal bd,BigDecimal minValue){
        bd = filterNull(bd);
        if(compare(bd,minValue,COMPARE_TYPE_LESS)){
            bd = minValue;
        }
        return bd;
    }
    public static BigDecimal minZero(BigDecimal bd){
        return minValue(bd,ZERO);
    }


    /*范围*/
    public static boolean among(BigDecimal value,BigDecimal min,BigDecimal max){
        ParamVerifyUtil.paramNotNull(value,"判断范围失败：判断值不能为空");
        ParamVerifyUtil.paramNotNull(min,"判断范围失败：最小值不能为空");
        ParamVerifyUtil.paramNotNull(max,"判断范围失败：最大值不可为空");
        if(BigDecimalUtils.greater(min,max)){
            ExceptionUtil.throwWarn("判断范围失败：最小值不可大于最大值");
        }

        if(BigDecimalUtils.greater(value,max) || BigDecimalUtils.less(value,min)){
            return false;
        }
        return true;
    }

    /*精度*/
    public static boolean availableScale(BigDecimal value,Integer scale){
        ParamVerifyUtil.paramNotNull(value,"判断精度失败：值不能为空");
        ParamVerifyUtil.paramNotNull(scale,"判断精度失败：精度不能为空");
        if(scale<0){
            ExceptionUtil.throwWarn("判断精度失败：精度不能小于0");
        }

        return BigDecimalUtils.equal(value,BigDecimalUtils.roundCeiling(value,scale));
    }
}
