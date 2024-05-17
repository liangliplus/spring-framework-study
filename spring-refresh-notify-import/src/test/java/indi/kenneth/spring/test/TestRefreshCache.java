package indi.kenneth.spring.test;

import indi.kenneth.spring.refresh.anno.RefreshCache;
import indi.kenneth.spring.refresh.notify.RefreshCacheService;
import org.springframework.stereotype.Component;


@RefreshCache(key = "/test/refresh",refreshTime = 3000)
@Component
public class TestRefreshCache  implements RefreshCacheService<String> {
    @Override
    public void refresh(String s) {
        System.out.println("接受到"+s);
    }

    @Override
    public void init() {

    }
}
