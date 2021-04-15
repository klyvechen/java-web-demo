package rm.project.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 加密Util
 * 將輸入的字串以Md5的方式加密並回傳加密結果
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public class EncryptUtil {

    /**
     * 對字串md5加密
     * 參考: https://www.ktanx.com/blog/p/4026
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) throws Exception{
        try {
            // 生成一個MD5加密計算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 計算md5函數
            md.update(str.getBytes());
            // digest()最後確定返回md5 hash值，返回值為8為字串。因為md5 hash值是16位的hex值，實際上就是8位元的字元
            // BigInteger函數則將8位元的字串轉換成16位元hex值，用字串來表示；得到字串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出現錯誤");
        }
    }

}
