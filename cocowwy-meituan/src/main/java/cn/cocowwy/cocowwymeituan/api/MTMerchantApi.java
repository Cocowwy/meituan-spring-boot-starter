package cn.cocowwy.cocowwymeituan.api;

import cn.cocowwy.cocowwymeituan.config.MeiTuanProperties;
import cn.cocowwy.cocowwymeituan.constant.RouteEnum;
import cn.cocowwy.cocowwymeituan.constant.StringPool;
import cn.cocowwy.cocowwymeituan.constant.URLPrefix;
import cn.cocowwy.cocowwymeituan.core.CoreUtil;
import cn.cocowwy.cocowwymeituan.core.SignGenerator;
import cn.cocowwy.cocowwymeituan.rq.MerchantStatusRequest;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import cn.cocowwy.cocowwymeituan.rs.Result;


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
        if (CoreUtil.convertToMap(request) != null) {
            map.putAll(CoreUtil.convertToMap(request));
        }

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.MERCHANT_URL_PREFIX, request.getStatus().name().equals("CLOSE")
                ? RouteEnum.CLOSE.getSuffix() : RouteEnum.OPEN.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createPost(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }


    /**
     * 查询 门店营业状态
     * @param appPoiCode
     * @return
     */
    public Result getMerchantStatus(String appPoiCode) {
        Assert.notNull(appPoiCode);

        Map<String, String> map = globalPropertiesMap;
        map.put("app_poi_codes", appPoiCode);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.MERCHANT_URL_PREFIX, RouteEnum.MGET.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createGet(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }

    private String createUrl(String spliceUrl, String sig) {
        return spliceUrl.replaceAll(meiTuanProperties.getAppSecret(), "") + SIGN + sig;
    }
}
