# 🍔cocowwy-meituan-starter
美团品牌商API封装，适用于SpringBoot项目，拆箱即用

## 前言
对美团品牌商提供的SDK源码进行抽取简化，并对官网提供的API，进行了一层封装，以便更简便的请求美团品牌商相关的API

pom.xml
```xml
<dependency>
    <groupId>cn.cocowwy</groupId>
    <artifactId>meituan-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

yml
```yml
meituan:
  appId:xxxx
  appSecret: xxx   
```

Demo
```java
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
```


**目前只提供可一些常用的接口请求功能，如果需要新增的话，可以提 issue，然后会对功能进行完善**
