package cn.fly.logDemo.utils;

/**
 * @author fly
 * @date 2023/2/23
 * @description
 */

public class BasicClassesChecker {

    private final String IntegerType = "java.lang.Integer.class";
    private final String ByteType = "java.lang.Byte.class";
    private final String LongType = "java.lang.Long.class";
    private final String DoubleType = "java.lang.Double.class";
    private final String FloatType = "java.lang.Float.class";
    private final String CharacterType = "java.lang.Character.class";
    private final String ShortType = "java.lang.Short.class";
    private final String BooleanType = "java.lang.Boolean.class";

    /**
     * 判断该类型是否为基础类型
     * @param clazz
     * @return
     */
    public static boolean check(Class<?> clazz) {
        return clazz.equals(Integer.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(String.class) ||
                clazz.equals(Boolean.class);
    }


}
