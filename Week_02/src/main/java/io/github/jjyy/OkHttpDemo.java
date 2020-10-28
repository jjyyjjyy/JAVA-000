package io.github.jjyy;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author jy
 */
public class OkHttpDemo {

    @SneakyThrows
    public static String get(String url) {
        Request request = new Request.Builder().get().url(url).build();
        Response response = new OkHttpClient().newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) {
        System.out.println(get("http://localhost:8801"));
    }
}
