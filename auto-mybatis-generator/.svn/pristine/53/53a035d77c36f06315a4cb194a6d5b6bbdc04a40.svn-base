package com.toolkit.auto.mybatis.utils;

import java.util.Random;

public class RandomUtils {
    private static final String RANDOM_CODE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static Random r = new Random();

    /**
     * 生成4位随机编码，可支撑36^4=1679616
     * 
     * @return
     */
    public static String getRandomShopCode() {
        Random random = new Random();
        char[] array = new char[4];
        for (int i = 0; i < 4; i++) {
            array[i] = RANDOM_CODE.charAt(random.nextInt(36));
        }
        return new String(array);
    }

    /**
     * @方法名: randomNum6
     * @方法描述: TODO(随机获取6位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:38:50
     * @修改时间 2013-10-18 上午11:38:50
     * @版本 V2.1
     * @异常
     */
    public static long randomNum6() {
        long x = r.nextInt(999999);
        if (x < 100000) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(999999);
                if (x > 100000) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: randomNum5
     * @方法描述: TODO(随机获取5位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:39:41
     * @修改时间 2013-10-18 上午11:39:41
     * @版本 V2.1
     * @异常
     */
    public static long randomNum5() {
        long x = r.nextInt(99999);
        if (x < 10000) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(99999);
                if (x > 10000) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: randomNum4
     * @方法描述: TODO(随机获取4位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:39:51
     * @修改时间 2013-10-18 上午11:39:51
     * @版本 V2.1
     * @异常
     */
    public static long randomNum4() {
        long x = r.nextInt(9999);
        if (x < 1000) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(9999);
                if (x > 1000) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: randomNum3
     * @方法描述: TODO(随机获取3位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:40:05
     * @修改时间 2013-10-18 上午11:40:05
     * @版本 V2.1
     * @异常
     */
    public static long randomNum3() {
        long x = r.nextInt(999);
        if (x < 100) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(999);
                if (x > 100) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: randomNum2
     * @方法描述: TODO(随机获取2位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:40:21
     * @修改时间 2013-10-18 上午11:40:21
     * @版本 V2.1
     * @异常
     */
    public static long randomNum2() {
        long x = r.nextInt(99);
        if (x < 10) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(99);
                if (x > 10) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: randomNum1
     * @方法描述: TODO(随机获取1位正整数)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:40:35
     * @修改时间 2013-10-18 上午11:40:35
     * @版本 V2.1
     * @异常
     */
    public static long randomNum1() {
        long x = r.nextInt(9);
        if (x < 1) {
            for (int i = 0; i < 100; i++) {
                x = r.nextInt(9);
                if (x > 1) {
                    break;
                }
            }
        }
        return x;
    }

    /**
     * @方法名: length10
     * @方法描述: TODO(随机生成10位数长度字符串)
     * @return
     * @返回值 String 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 上午11:45:32
     * @修改时间 2013-10-18 上午11:45:32
     * @版本 V2.1
     * @异常
     */
    public static String strLength10() {
        String randomStr = String.valueOf(randomNum6()) + String.valueOf(randomNum4());
        return randomStr;
    }

    /**
     * @方法名: longLength10
     * @方法描述: TODO(随机生成10位long类型数据)
     * @return
     * @返回值 long 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 下午12:19:11
     * @修改时间 2013-10-18 下午12:19:11
     * @版本 V2.1
     * @异常
     */
    public static long longLength10() {
        String randomStr = String.valueOf(randomNum6()) + String.valueOf(randomNum4());
        return Long.valueOf(randomStr);
    }

    /**
     * @方法名: shortLength6
     * @方法描述: TODO(随机生成6位short类型数据)
     * @return
     * @返回值 short 返回类型
     * @作者：Fu Shihua
     * @创建时间 2013-10-18 下午12:19:11
     * @修改时间 2013-10-18 下午12:19:11
     * @版本 V2.1
     * @异常
     */
    public static Long longLength6() {
        String randomStr = String.valueOf(randomNum4()) + String.valueOf(randomNum2());
        return Long.valueOf(randomStr);
    }
}
