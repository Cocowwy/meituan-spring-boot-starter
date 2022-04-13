package cn.cocowwy.cocowwymeituan.rs;

/**
 * @author cocowwy.cn
 * @create 2022-02-02-18:43
 */
public class Result {
    private String data;

    private Error error;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data='" + data + '\'' +
                ", error=" + error +
                '}';
    }

    public static class Error {
        private String code;

        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}