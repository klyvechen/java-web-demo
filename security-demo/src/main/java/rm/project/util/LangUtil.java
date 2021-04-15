package rm.project.util;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 語系Util
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public class LangUtil {

    /**
     * 由message_lang_country裡存放字串，並由對應的語系及國家檢索出對應的語言
     * 用來使用跨國語系使用之工具方法
     *
     * @param language 語言類別: ch, jp, en
     * @param country  國家: TW, JP, US
     * @param key 對應關鍵字
     * @return 從Resource裡所得到的對應字串
     */
    public static String getMessage(String language ,String country, String key) {
        Locale locale = new Locale(language, country);
        ResourceBundle message = ResourceBundle.getBundle("message", locale);
        return message.getString(key);
    }

}
