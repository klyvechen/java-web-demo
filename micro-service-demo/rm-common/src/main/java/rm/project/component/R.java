package rm.project.component;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Map<String, Object> content;

    private String msg;

    public Object get(String key) {
        return content.get(key);
    }

    // 0 fail, 1 success;
    private int code;

    public static RBuilder ok() {
        return new RBuilder(REnum.SUCCESS);
    }

    public static class RBuilder {

        private R result;

        public RBuilder(REnum rEnum) {
            result = new R();
            result.code = rEnum.code;
            result.msg = rEnum.msg;
        }

        public RBuilder put(String key, Object value) {
            if (result.content == null) {
                result.content = new HashMap();
            }
            result.content.put(key, value);
            return this;
        }

        public R done() {
            return result;
        }
    }
}
