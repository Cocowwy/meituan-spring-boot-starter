package cn.cocowwy.cocowwymeituan.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-13:53
 */
@ConfigurationProperties(prefix = "meituan")
public class MeiTuanProperties {
    private String appId;
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
