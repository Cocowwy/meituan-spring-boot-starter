package cn.cocowwy.meituancore.rq;

/**
 * @author cocowwy.cn
 * @create 2021-12-12-17:08
 */
public class CategoryRequest {

    /**
     * 门店唯一标识
     */
    private String app_poi_code;

    /**
     * 菜谱分类名称
     */
    private String category_name;

    /**
     * 旧菜谱名称
     */
    private String category_name_origin;

    /**
     * 描述
     */
    private String category_description;

    /**
     * 排序
     */
    private String sequence;


    public String getApp_poi_code() {
        return app_poi_code;
    }

    public void setApp_poi_code(String app_poi_code) {
        this.app_poi_code = app_poi_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name_origin() {
        return category_name_origin;
    }

    public void setCategory_name_origin(String category_name_origin) {
        this.category_name_origin = category_name_origin;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
