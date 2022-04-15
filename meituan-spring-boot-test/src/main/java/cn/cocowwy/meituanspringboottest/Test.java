package cn.cocowwy.meituanspringboottest;

import cn.cocowwy.cocowwymeituan.api.MTMerchantApi;
import cn.cocowwy.cocowwymeituan.api.MTOrderApi;
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
    @Autowired
    private MTMerchantApi mtMerchantApi;


    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public void run(ApplicationArguments args) {
        // 获取订单明细
        Result result1 = mtOrderApi.orderDetail("123123");

        // 获取商户状态
        Result result2 = mtMerchantApi.getMerchantStatus("111");

        // 商家接收订单
        Result result3 = mtOrderApi.confirmOrder("123123");
    }
}
