package io.github.jjyy;

/**
 * 字节码指令查看
 *
 * @author jy
 */
public class Hello {

    public static void main(String[] args) {
        int result = 1;
        for (int i = 0; i < 100; i++) {
            result = result * 10 + i;
            if (result > 100) {
                break;
            }
        }
    }
}
