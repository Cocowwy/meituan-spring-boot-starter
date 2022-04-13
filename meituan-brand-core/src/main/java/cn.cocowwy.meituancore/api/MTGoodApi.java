package cn.cocowwy.meituancore.api;

import cn.cocowwy.cocowwymeituan.configuration.MeiTuanProperties;
import cn.cocowwy.meituancore.constant.RouteEnum;
import cn.cocowwy.meituancore.constant.StringPool;
import cn.cocowwy.meituancore.constant.URLPrefix;
import cn.cocowwy.meituancore.core.CoreUtil;
import cn.cocowwy.meituancore.core.SignGenerator;
import cn.cocowwy.meituancore.rq.CategoryRequest;
import cn.cocowwy.meituancore.rs.Result;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * 商品类API
 * @author cocowwy.cn
 * @create 2021-12-12-19:05
 */
public class MTGoodApi {
    @Autowired
    private MeiTuanProperties meiTuanProperties;
    @Autowired
    private Map<String, String> globalPropertiesMap;

    /**
     * 新增菜谱分类
     *  - 分类是空的则在用户端看不到
     */
    public Result addCategory(CategoryRequest request) throws Exception {
        Assert.notNull(request.getCategory_name());
        Assert.notNull(request.getCategory_description());
        Assert.notNull(request.getSequence());

        request.setCategory_name_origin(null);

        Map<String, String> map = globalPropertiesMap;
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        if (CoreUtil.convertToMap(request) != null) {
            map.putAll(CoreUtil.convertToMap(request));
        }

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.FOOD_CAT_URL_PREFIX, RouteEnum.FOOD_CAT_UPDATE.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createPost(CoreUtil.createUrl(meiTuanProperties, spliceUrl, sig)).execute().body(), Result.class);
    }

    /**
     * 修改菜谱分类
     */
    public Result updateCategory(CategoryRequest request) throws Exception {
        Assert.notNull(request.getCategory_name());

        Map<String, String> map = globalPropertiesMap;
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        if (CoreUtil.convertToMap(request) != null) {
            map.putAll(CoreUtil.convertToMap(request));
        }

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.FOOD_CAT_URL_PREFIX, RouteEnum.FOOD_CAT_UPDATE.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createPost(CoreUtil.createUrl(meiTuanProperties, spliceUrl, sig)).execute().body(), Result.class);
    }

    /**
     * 刪除菜谱分类
     *  - 该分类下商品也会清空
     */
    public Result deleteCategory(CategoryRequest request) throws Exception {
        Assert.notNull(request.getCategory_name());

        Map<String, String> map = globalPropertiesMap;
        map.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        if (CoreUtil.convertToMap(request) != null) {
            map.putAll(CoreUtil.convertToMap(request));
        }

        String spliceUrl = StrUtil.format(StringPool.REQUEST_TEMPLETE, URLPrefix.FOOD_CAT_URL_PREFIX, RouteEnum.FOOD_CAT_DELETE.getSuffix(), CoreUtil.concatParams(map));
        String sig = SignGenerator.genSig(spliceUrl + meiTuanProperties.getAppSecret());
        return JSONObject.parseObject(HttpUtil.createPost(CoreUtil.createUrl(meiTuanProperties, spliceUrl, sig)).execute().body(), Result.class);
    }
}
