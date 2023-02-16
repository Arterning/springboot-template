package cn.ning.springboot.starter.util;

import cn.ning.springboot.starter.entity.SimpleUser;
import cn.ning.springboot.starter.exception.UnloginException;
import org.slf4j.MDC;

import java.util.Locale;

public class UserUtil {

    private final static ThreadLocal<SimpleUser> tlUser = new ThreadLocal<>();

    private final static ThreadLocal<Locale> tlLocale = ThreadLocal.withInitial(() -> {
        // 语言的默认值
        return Locale.CHINESE;
    });

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(SimpleUser user) {
        tlUser.set(user);

        // 把用户信息放到log4j
        MDC.put(KEY_USER, user.getUsername());
    }

    /**
     * 如果没有登录，返回null
     *
     * @return
     */
    public static SimpleUser getUserIfLogin() {
        return tlUser.get();
    }

    /**
     * 如果没有登录会抛出异常
     *
     * @return
     */
    public static SimpleUser getUser() {
        SimpleUser user = tlUser.get();

        if (user == null) {
            throw new UnloginException();
        }

        return user;
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();

        MDC.remove(KEY_USER);
    }
}
