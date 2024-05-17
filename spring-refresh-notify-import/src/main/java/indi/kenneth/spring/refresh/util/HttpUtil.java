package indi.kenneth.spring.refresh.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * 目前为okHttp 实现
 */
public class HttpUtil {
    private static ConnectionPool connectionPool;
    private static OkHttpClient okHttpClient;

    static {
        connectionPool = new ConnectionPool(100, 10L, TimeUnit.MINUTES);
        okHttpClient = (new OkHttpClient.Builder()).connectTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).connectionPool(connectionPool).build();
    }

    public HttpUtil() {
    }

    public static JSONObject getJson(String url, JSONObject param) throws Throwable {
        return get(url, param, (Map)null);
    }

    public static String getStr(String url, JSONObject param, Map header) throws Throwable {
        Response response = getResponse(url, param, header);
        String resultStr = response.body().string();
        return resultStr;
    }

    public static JSONObject get(String url, JSONObject param, Map header) throws Throwable {
        JSONObject json = JSONObject.parseObject(getStr(url, param, header));
        return json;
    }

    public static Response getResponse(String url, JSONObject param, Map header) throws Throwable {
        StringJoiner stringJoiner = new StringJoiner("&", url + "?", "");
        if (param != null) {
            Set<String> keys = param.keySet();
            keys.forEach((li) -> {
                Object value = param.get(li);
                String kv = li + "=" + value;
                stringJoiner.add(kv);
            });
            url = stringJoiner.toString();
        }

        if (header == null) {
            header = new HashMap();
        }

        Headers headers = Headers.of((Map)header);
        Request request = (new Request.Builder()).url(url).headers(headers).get().build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }


}
