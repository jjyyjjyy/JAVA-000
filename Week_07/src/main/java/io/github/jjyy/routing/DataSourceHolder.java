package io.github.jjyy.routing;

/**
 * @author jy
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> DATA_SOURCE = new InheritableThreadLocal<>();

    public static void set(String id) {
        DATA_SOURCE.set(id);
    }

    public static void clear() {
        DATA_SOURCE.remove();
    }

    public static String get() {
        return DATA_SOURCE.get();
    }
}
