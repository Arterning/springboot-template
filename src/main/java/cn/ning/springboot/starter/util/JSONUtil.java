package cn.ning.springboot.starter.util;

/**
 * @Description JSON工具类
 * 为什么fastjson已经有了工具类还要自己封装呢？
 * 是因为需要隔离具体实现。如果替换了json库，其他代码不需要改动。
 */
public class JSONUtil {
    public static String toJsonStr(Object object) {

        //TODO return JSON.toJSONString(object);
        return "";

    }
}
