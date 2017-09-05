package cn.cuco.test;

import cn.cuco.common.utils.date.DateUtils;
import org.junit.Test;

/**
 * @ClassName:
 * @Description：
 * @author：WangShuai
 * @date：2017年02月28 15:47:00
 */
public class DateUtilsTest {

    @Test
    public void test(){
        System.out.println(DateUtils.parseDate("2017d*11"));
    }
}
