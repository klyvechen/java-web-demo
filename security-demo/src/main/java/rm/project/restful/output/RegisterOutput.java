package rm.project.restful.output;

import lombok.Getter;

/**
 * 註冊成功Response
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Getter
public class RegisterOutput {

    final private String token;

    /**
     * 註冊成功物件 回傳token
     * @param token
     */
    public RegisterOutput(String token) {
        this.token = token;
    }

}
