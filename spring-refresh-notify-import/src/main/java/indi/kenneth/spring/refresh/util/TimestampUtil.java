package indi.kenneth.spring.refresh.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampUtil.class);

    private static final String TAOBAO_TIMESTAMP_URL = "http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp";


    /**
     * 获取时间戳
     * @return
     */
    public static long get() {
        try {
            JSONObject json = HttpUtil.getJson(TAOBAO_TIMESTAMP_URL, null);
            return json.getJSONObject("data").getLong("t");
        } catch (Throwable e) {
            LOGGER.warn("taobao timestamp call error url:{}",TAOBAO_TIMESTAMP_URL,e);
            return System.currentTimeMillis();
        }
    }
}
