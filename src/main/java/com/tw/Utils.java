package com.tw;

import java.util.List;

/**
 * @Author Joker
 * @Description
 * @Date Create in 下午2:43 2018/4/9
 */
public class Utils {
    private Utils(){}

    public static float getMidNumberFromList(List<Integer> list) {
        float mid;
        int size = list.size();
        int index = size / 2;
        if (size % 2 == 0) {
            mid = (float) (list.get(index) + list.get(index - 1)) / 2;
        } else {
            mid = list.get(index);
        }
        return mid;
    }

}
