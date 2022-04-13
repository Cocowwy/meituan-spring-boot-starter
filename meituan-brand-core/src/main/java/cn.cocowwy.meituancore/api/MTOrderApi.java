package cn.cocowwy.meituancore.api;


import cn.cocowwy.cocowwymeituan.configuration.MeiTuanProperties;
import cn.cocowwy.meituancore.constant.RouteEnum;
import cn.cocowwy.meituancore.constant.StringPool;
import cn.cocowwy.meituancore.constant.URLPrefix;
import cn.cocowwy.meituancore.core.CoreUtil;
import cn.cocowwy.meituancore.core.SignGenerator;
import cn.cocowwy.meituancore.rs.Result;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * @author cocowwy.cn
 * @create 2021-12-12-14:11
 */
public class MTOrderApi {
    @Autowired
    private MeiTuanProperties meiTuanProperties;
    @Autowired
    private Map<String, String> globalPropertiesMap;

    /**
     * 商家确认接单
     * @param orderId 美团方订单ID
     * @return
     */
    public Result confirmOrder(String orderId) {
        Assert.notNull(orderId);

        Map<String, String> map = globalPropertiesMap;
        map.put("order_id", orderId);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.ORDER_URL_PREFIX, RouteEnum.CONFIRM.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());

        return JSONObject.parseObject(HttpUtil.createGet(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }

    /**
     * 商家取消订单
     * @param orderId 美团方订单ID
     * @return
     */
    public Result cancelOrder(String orderId, String reason, String reason_code) {
        Assert.notNull(orderId);
        Assert.notNull(reason);
        Assert.notNull(reason_code);

        Map<String, String> map = globalPropertiesMap;
        map.put("order_id", orderId);
        map.put("reason", reason);
        map.put("reason_code", reason_code);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.ORDER_URL_PREFIX, RouteEnum.CANCEL.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());

        return JSONObject.parseObject(HttpUtil.createGet(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }

    /**
     * 获取订单详细信息
     * @param orderId 美团方订单ID
     * @return
     */
    public Result orderDetail(String orderId) {
        Assert.notNull(orderId);
        Map<String, String> map = globalPropertiesMap;
        map.put("order_id", orderId);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.ORDER_URL_PREFIX, RouteEnum.GET_ORDER_DETAIL.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());

        return JSONObject.parseObject(HttpUtil.createGet(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }

    /**
     * 出餐
     * @param orderId 美团方订单ID
     * @return
     */
    public Result diningOut(String orderId) {
        Assert.notNull(orderId);
        Map<String, String> map = globalPropertiesMap;
        map.put("order_id", orderId);

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.ORDER_URL_PREFIX, RouteEnum.PREPARATION_MEAL_COMPLETE.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());

        return JSONObject.parseObject(HttpUtil.createGet(createUrl(spliceUrl, sig)).execute().body(), Result.class);
    }

    private String createUrl(String spliceUrl, String sig) {
        return spliceUrl.replaceAll(meiTuanProperties.getAppSecret(), "") + StringPool.SIGN + sig;
    }
}
