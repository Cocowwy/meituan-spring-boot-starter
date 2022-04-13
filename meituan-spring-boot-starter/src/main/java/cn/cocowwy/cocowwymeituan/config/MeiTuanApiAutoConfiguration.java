package cn.cocowwy.cocowwymeituan.config;


import cn.cocowwy.cocowwymeituan.api.MTGoodApi;
import cn.cocowwy.cocowwymeituan.api.MTMerchantApi;
import cn.cocowwy.cocowwymeituan.api.MTOrderApi;
import cn.cocowwy.cocowwymeituan.core.CoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-13:53
 */
@Configuration
@EnableConfigurationProperties(MeiTuanProperties.class)
public class MeiTuanApiAutoConfiguration {
    @Autowired
    private MeiTuanProperties meiTuanProperties;

    @Bean
    public MTGoodApi mtGoodApi() {
        return new MTGoodApi();
    }

    @Bean
    public MTMerchantApi mtMerchantApi() {
        return new MTMerchantApi();
    }

    @Bean
    public MTOrderApi mtOrderApi() {
        return new MTOrderApi();
    }

    @Bean
    public CoreUtil coreUtil() {
        return new CoreUtil();
    }

    @Bean
    public Map<String, String> globalPropertiesMap() {
        Map<String, String> resultMap = new HashMap();
        resultMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        resultMap.put("app_id", meiTuanProperties.getAppId());
        resultMap.put("appSecret", meiTuanProperties.getAppSecret());
        return resultMap;
    }
}
