package indi.liangli.spring.webclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @Author liangll
 * @Date 2023/5/26
 */
public class WebClientTest {




    @Test
    public void test() {
        String baseUrl = "https://postman-echo.com/post";
        String json = "[\"1\",\"2\"]";
        WebClient webClient = WebClient.create(baseUrl);
        Mono<String> stringMono = webClient.post()
                .header("test","a")
                .body(BodyInserters.fromValue(json))
                .retrieve()
                .bodyToMono(String.class);
        Mono<Object> data1 = stringMono.map(s -> {
            HashMap<String, Object> hashMap = JSON.parseObject(s, new TypeReference<HashMap<String, Object>>() {
            });
            Object data = hashMap.get("data");
            System.out.println(data);
            return data;
        }).ofType(Object.class);
        System.out.println(data1.block());

    }


}
