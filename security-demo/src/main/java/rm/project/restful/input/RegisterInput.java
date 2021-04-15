package rm.project.restful.input;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * 註冊RequestInput
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Data
public class RegisterInput {

    @NonNull
    /** 姓名　*/
    private String name;

    /** Email確認: 0, 手機確認: 1　*/
    private int type;

    /** 國家 */
    private String country;

    /** 語言 */
    private String language;

    /** 密碼 */
    private String password;

    /** 確認密碼 */
    private String checkPassword;

    /** 註冊值 */
    private String value;

    public String toString() {
        return new Gson().toJson(this);
    }

    public RegisterInput(){}
}
