package cn.cocowwy.meituanspringboottest;

import cn.cocowwy.meituancore.api.MTGoodApi;
import cn.cocowwy.meituancore.api.MTOrderApi;
import cn.cocowwy.meituancore.rs.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Cocowwy
 * @create 2022-04-04-20:52
 */
@Component
public class Test implements ApplicationRunner {
    @Autowired
    private MTOrderApi mtOrderApi;

    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public void run(ApplicationArguments args) throws Exception {
        Result result = mtOrderApi.orderDetail("123123");
    }
}
