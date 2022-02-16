package cn.cocowwy.cocowwymeituan.rq;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-11:54
 */
public class MerchantStatusRequest {
    /**
     * APP方 门店ID
     */
    private String app_poi_code;

    /**
     * 推送的门店状态  OPEN / CLOSE
     */
    private Status status;

    /**
     * 原因
     */
    private String reason;

    public String getApp_poi_code() {
        return app_poi_code;
    }

    public void setApp_poi_code(String app_poi_code) {
        this.app_poi_code = app_poi_code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public enum Status {
        OPEN,
        CLOSE
    }
}
