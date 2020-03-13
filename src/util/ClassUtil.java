package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("unused")
public class ClassUtil {


    /**
     * @author lilei
     * create at 2020/2/24 14:20
     * 获取特定类型的属性名数组(包含父类)
     */
    public static String[] getFiledName(Object o, Class<?> clazz) {
        Field[] fields = getFields(o.getClass());

        String[] fieldNames = new String[fields.length];
        int num = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType() == clazz) {
                fieldNames[i] = fields[i].getName();
                num++;
            }
        }
        String[] full = new String[num];
        System.arraycopy(fieldNames, 0, full, 0, num);
        return full;
    }

    /**
     * @author lilei
     * create at 2020/2/24 15:52
     * 取得所有字段（包含所有父类的任意类型字段）
     */
    public static Field[] getFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return fieldList.toArray(new Field[0]);
    }

    /**
     * @author lilei
     * create at 2020/2/24 15:52
     * 取得特定类型字段（包含所有父类的特定类型字段）
     */
    public static Field[] getFields(Class<?> clazz, Class<?> expect) {
        Field[] fields = getFields(clazz);
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields) {
            if (field.getType() == expect) {
                fieldList.add(field);
            }
        }
        return fieldList.toArray(new Field[0]);
    }


    /**
     * @author lilei
     * create at 2020/2/24 14:37
     * 去除对象中string字段的前后空格，
     */
    public static void trim(Object o) throws IllegalAccessException {
        Field[] fields = getFields(o.getClass(), String.class);
        for (Field field : fields) {
            field.setAccessible(true);
            String value = (String) field.get(o);
            value = StringUtil.trimWhitespace(value);
            field.set(o, value);
        }
    }
}
