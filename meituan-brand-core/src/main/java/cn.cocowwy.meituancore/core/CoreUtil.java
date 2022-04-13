package cn.cocowwy.meituancore.core;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author cocowwy.cn
 * @create 2021-12-12-11:33
 */
public class CoreUtil {
    public static Map<String, String> convertToMap(Object object) throws Exception {
        HashMap resultMap = new HashMap();

        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Field[] arr$ = fields;
            int len$ = fields.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                Field field = arr$[i$];
                field.setAccessible(true);
                Class typeClazz = field.getType();
                String key = field.getName();
                String val = null;
                if (List.class.isAssignableFrom(typeClazz)) {
                    val = JSON.toJSONString(field.get(object));
                } else {
                    val = String.valueOf(field.get(object));
                }

                if (val != null && !"".equals(val) && !"null".equals(val) && !"NULL".equals(val)) {
                    resultMap.put(key, val);
                }
            }

            return resultMap;
        } catch (Exception var10) {
            throw new RuntimeException("系统错误");
        }
    }

    /**
     * 美团参数
     * @param params
     * @return
     */
    public static String concatParams(Map<String, String> params) {
        Object[] key_arr = params.keySet().toArray();
        Arrays.sort(key_arr);
        String str = "";
        Object[] arr$ = key_arr;
        int len$ = key_arr.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            Object key = arr$[i$];
            if (!key.equals("appSecret")) {
                String val = (String) params.get(key);
                str = str + "&" + key + "=" + val;
            }
        }
        return str.replaceFirst("&", "");
    }
}
