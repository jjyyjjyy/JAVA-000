package io.github.jjyy;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jy
 */
public class HelloClassLoader {

    public static void main(String[] args) {
        XClassLoader xClassLoader = new XClassLoader();
        try {
            Class<?> helloClass = Class.forName("Hello", true, xClassLoader);
            Object hello = helloClass.getDeclaredConstructor().newInstance();
            helloClass.getMethod("hello").invoke(hello);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class XClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) {
            byte[] bytes = readFile(name);
            return defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] readFile(String name) {
            try {
                URL xlassFile = getClass().getClassLoader().getResource(name + ".xlass");
                if (xlassFile == null) {
                    throw new RuntimeException(name);
                }
                byte[] file = Files.readAllBytes(Paths.get(xlassFile.toURI()));
                byte[] bytes = new byte[file.length];
                for (int i = 0; i < file.length; i++) {
                    bytes[i] = (byte) (255 - file[i]);
                }
                return bytes;
            } catch (Exception e) {
                throw new RuntimeException(name);
            }
        }
    }
}
