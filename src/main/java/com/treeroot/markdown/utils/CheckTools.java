package com.treeroot.markdown.utils;

import java.util.Collection;

/**
 * @author xugenyin
 */
public class CheckTools {
    private static  final   String  IS_NULL ="null";
    /**
     * 判断对象是否为Null或空
     *
     * @param obj 需要判断的对象
     * @return True:空 False：非空
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {

            if (((String) obj).isEmpty() || IS_NULL.equals(obj)) {
                return true;
            }
        }
        if (obj instanceof String[]) {
            if (((String[]) obj).length == 0) {
                return true;
            }
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        return false;
    }

    /**
     * 判断对象是否不为Null或非空
     *
     * @param obj 需要判断的对象
     * @return True:非空 False：空
     */
    public static boolean isNotNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }
}
