package cn.cocowwy.meituancore.api;


import cn.cocowwy.cocowwymeituan.configuration.MeiTuanProperties;
import cn.cocowwy.meituancore.constant.RouteEnum;
import cn.cocowwy.meituancore.constant.StringPool;
import cn.cocowwy.meituancore.constant.URLPrefix;
import cn.cocowwy.meituancore.core.CoreUtil;
import cn.cocowwy.meituancore.core.SignGenerator;
import cn.cocowwy.meituancore.rq.MerchantStatusRequest;
import cn.cocowwy.meituancore.rs.Result;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * 商户门店类API
 *
 * return 请求响应的body,返回信息以美团开放平台文档为准
 *
 * @author cocowwy.cn
 * @create 2021-12-12-19:06
 */
public class MTMerchantApi {
    @Autowired
    private MeiTuanProperties meiTuanProperties;
    @Autowired
    private Map<String, String> globalPropertiesMap;

    /**
     * 设置 门店营业状态
     * @param request
     * @return
     * @throws Exception
     */
    public Result setMerchantStatus(MerchantStatusRequest request) throws Exception {
        Assert.notNull(request.getApp_poi_code());
        Assert.notNull(request.getStatus());

        Map<String, String> map = globalPropertiesMap;
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        if (CoreUtil.convertToMap(request) != null) {
            map.putAll(CoreUtil.convertToMap(request));
        }

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.MERCHANT_URL_PREFIX, request.getStatus().name().equals("CLOSE")
                ? RouteEnum.CLOSE.getSuffix() : RouteEnum.OPEN.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createPost(CoreUtil.createUrl(meiTuanProperties, spliceUrl, sig)).execute().body(), Result.class);
    }


    /**
     * 查询 门店营业状态
     * @param appPoiCode
     * @return
     */
    public Result getMerchantStatus(String appPoiCode) {
        Assert.notNull(appPoiCode);

        Map<String, String> map = globalPropertiesMap;
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        map.put("app_poi_codes", appPoiCode);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.MERCHANT_URL_PREFIX, RouteEnum.MGET.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createGet(CoreUtil.createUrl(meiTuanProperties, spliceUrl, sig)).execute().body(), Result.class);
    }


}
