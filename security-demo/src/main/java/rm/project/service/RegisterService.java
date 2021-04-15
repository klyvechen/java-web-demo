package rm.project.service;

import rm.project.dao.UserRepository;
import rm.project.enums.RegisterTypeEnum;
import rm.project.model.User;
import rm.project.enums.RegisterEnum;
import rm.project.restful.input.RegisterInput;
import rm.project.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 註冊Service
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    private final static String TW_PHONE_FORMAT = "(09\\d{8}$)|(9\\d{8}$)|(8869\\d{8}$)|(008869\\d{8}$)|(886-9\\d{8}$)|(00886-9\\d{8}$)";

    private final static String EMAIL_FORMAT = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";

    /**
     * 取得為手機或email的type
     *
     * @param type 0為email 1為手機
     * @return RegisterTypeEnum
     */
    public RegisterTypeEnum getRegisterType(Integer type) {
        return RegisterTypeEnum.get(type);
    }

    /**
     * 確認email
     * 1. 確認email是否為空值或是格式是否正確
     * 2. 確認email是否已被註冊過
     *
     * @param email 註冊email
     * @return RegisterEnum
     */
    public RegisterEnum checkEmail(String email) {
        if (email == null) {
            return RegisterEnum.INPUT_EXCEPTION;
        }
        if (!checkFormat(email, EMAIL_FORMAT)) {
            return RegisterEnum.EMAIL_FORMAT_EXCEPTION;
        }
        List<User> users = userRepository.findByEmail(email);
        if (users.size() == 0) {
            return RegisterEnum.REGISTER_SUCCESS;
        } else {
            return RegisterEnum.EMAIL_REGISTERED_EXCEPTION;
        }
    }

    /**
     * 確認email
     * 1. 確認手機號碼是否為空值或是格式是否正確
     * 2. 確認手機號碼是否已被註冊過
     *
     * @param phone　手機號碼
     * @return RegisterEnum
     */
    public RegisterEnum checkPhone(String phone) {
        if (phone == null) {
            return RegisterEnum.INPUT_EXCEPTION;
        }
        if (!checkFormat(phone, TW_PHONE_FORMAT)) {
            return RegisterEnum.PHONE_FORMAT_EXCEPTION;
        }
        List<User> users = userRepository.findByPhone(phone);
        if (users.size() == 0) {
            return RegisterEnum.REGISTER_SUCCESS;
        } else {
            return RegisterEnum.PHONE_REGISTERED_EXCEPTION;
        }
    }

    /**
     * 確認是否符合格式
     *
     * @param target　預備確認的值
     * @param regex　手機或email的格式內容
     * @return true為成功
     */
    private boolean checkFormat(String target, String regex) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(target).find();
    }

    /**
     * 檢查密碼
     * 1. 檢查密碼長度
     * 2. 檢查是否與確認密碼相符合
     *
     * @param password 密碼
     * @param checkPassword 確認密碼
     * @return RegisterEnum
     */
    public RegisterEnum checkPassword(String password, String checkPassword) {
        // 檢查密碼是否大於八個字元
        if (password == null || password.length() < 8) {
            return RegisterEnum.PASSWORD_LENGTH_EXCEPTION;
        }

        // 檢查確認密碼是否相同
        if (!password.equals(checkPassword)) {
            return RegisterEnum.PASSWORD_CHECK_EXCEPTION;
        }
        return RegisterEnum.REGISTER_SUCCESS;
    }

    /**
     * 新增會員
     *
     * @param input　註冊request
     * @param type 手機或是email,　若type為1 則為手機, 0為email
     * @throws Exception
     */
    public void saveRegister(RegisterInput input, RegisterTypeEnum type) throws Exception {
        try {
            final String value = input.getValue();
            final User user = User.builder()
                    .name(input.getName())
                    .password(EncryptUtil.getMD5(input.getPassword()))
                    .email(type == RegisterTypeEnum.EMAIL ? value : null)
                    .phone(type == RegisterTypeEnum.PHONE ? value : null)
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
