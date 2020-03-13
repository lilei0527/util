package util;

import java.text.DecimalFormat;

/**
 * @author lilei
 * create at 2020/2/13 13:48
 */
public class MathUtil {

    /**
     * @author lilei
     * create at 2020/2/13 13:49
     * a/b 保留 hold 位小数
     */
    public static float division(float a, float b, int hold) throws RuntimeException {
        if (hold < 0) {
            throw new RuntimeException("保留位数不能为负数");
        }
        if (b == 0) {
            throw new RuntimeException("除数不能为0");
        }

        String pattern;
        StringBuilder buffer = new StringBuilder("0.");

        if (hold == 0) {
            pattern = buffer.substring(0, 1);
        } else {
            for (int i = 0; i < hold; i++) {
                buffer.append("0");
            }
            pattern = buffer.toString();
        }

        DecimalFormat df = new DecimalFormat(pattern);//设置保留位数
        return Float.parseFloat(df.format(a / b));
    }

    public static void main(String[] args) throws RuntimeException {
        System.out.println(division(0.1f, 0.3f, 2));
    }

}
