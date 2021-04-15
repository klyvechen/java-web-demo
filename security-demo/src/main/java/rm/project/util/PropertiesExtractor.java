package rm.project.util;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Resource Util
 * 取得yml內容的Util, 為了服務某些Static方法, Spring本身＠Value無法在static編譯階段得到該結果
 * 這裡使用Yaml物件, 在static階段便ymal的物件取出並暫存使用.
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public class PropertiesExtractor {

    private static Map<String, Object> yamContent;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = PropertiesExtractor.class
                .getClassLoader()
                .getResourceAsStream("application.yml");
        yamContent = yaml.load(inputStream);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(yamContent);
    }

    public static <T> T getProperty(String key){
        String[] keys = key.split("\\.");
        Object obj = yamContent;
        for (String k : keys) {
            obj = ((Map<String, Object>) obj).get(k);
        }
        return (T) obj;
    }
}
