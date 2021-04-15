package rm.project.restful.output;

import rm.project.util.LangUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.UnsupportedEncodingException;

/**
 * 錯誤訊息
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Data
public class ErrorOutput {

    @JsonProperty("error_message")
    private String errorMessage;

    /**
     * 錯誤回傳物件
     * @param language 語言
     * @param country 國家
     * @param key 錯誤代碼
     */
    public ErrorOutput(String language, String country, String key) {
        if (language == null || country == null) {
            language = "ch";
            country = "tw";
        }
        String errorMessage = LangUtil.getMessage(language, country, key);
        try {
            this.errorMessage = new String(errorMessage.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Exception occur");
            this.errorMessage = errorMessage;
        }
    }
}
