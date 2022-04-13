package cn.cocowwy.meituancore.constant;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-11:19
 */
public enum RouteEnum {
    /**向美团平台同步自配订单配送信息*/
    RIDER_POSITION("riderPosition"),

    /**门店设置为休息状态*/
    CLOSE("close"),

    /**门店设置为营业状态*/
    OPEN("open"),

    /**获取所有门店列表*/
    MGET("mget"),

    /**设订单为商家已收到*/
    CONFIRM("confirm"),

    /**驳回订单退款申请*/
    REFUND_REJECT("refund/reject"),

    /**订单确认退款请求*/
    REFUND_AGREE("refund/agree"),

    /**拉取用户真实手机号*/
    BATCH_PULL_PHONE_NUMBER("batchPullPhoneNumber"),

    /**商家取消订单*/
    CANCEL("cancel"),

    /**获取商品列表*/
    LIST("list"),

    /**出餐*/
    PREPARATION_MEAL_COMPLETE("preparationMealComplete"),

    /**商品上下架*/
    SKU_SELL_STATUS("sku/sellStatus"),

    /**修改app_food_code*/
    UPDATE_APPFOODCODE_BY_NAME_SPEC("updateAppFoodCodeByNameAndSpec"),

    /**批量创建或更新菜品*/
    BATCH_INIT_DATA("batchinitdata"),

    /**上传图片*/
    UPLOAD("upload"),

    /**获取订单详细信息*/
    GET_ORDER_DETAIL("getOrderDetail"),

    /**修改营业时间*/
    SHIPPINGTIME_UPDATE("shippingtime/update"),

    /**菜品删除*/
    FOOD_DELETE("delete"),

    /**更新*/
    FOOD_CAT_UPDATE("update"),

    /**目录删除*/
    FOOD_CAT_DELETE("delete");

    RouteEnum(String suffix) {
        this.suffix = suffix;
    }

    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
