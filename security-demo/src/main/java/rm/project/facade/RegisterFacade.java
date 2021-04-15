package rm.project.facade;

import rm.project.enums.RegisterTypeEnum;
import rm.project.restful.input.RegisterInput;
import rm.project.service.MailService;
import rm.project.service.RegisterService;
import rm.project.enums.RegisterEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 註冊Facade
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Service
public class RegisterFacade {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private RegisterService registerService;
    @Autowired
    private MailService mailService;

    /**
     * 對input值進行驗證
     * @param input
     * @return 驗證是否有參數例外
     */
    public RegisterEnum checkInput(RegisterInput input) {
        logger.info(input);
        if (input.getName() == null || input.getPassword() == null) {
            return RegisterEnum.INPUT_EXCEPTION;
        }
        return RegisterEnum.REGISTER_SUCCESS;
    }

    /**
     * 對input值進行註冊,
     * @param input
     * @return 成功回傳 REGISTER_SUCCESS, 其餘失敗
     */
    public RegisterEnum register(RegisterInput input) throws Exception {

        // 1. 確認為手機註冊或Email註冊
        RegisterTypeEnum type = RegisterTypeEnum.get(input.getType());

        // 2. 確認密碼或手機是否被使用過
        RegisterEnum result;
        if (type == RegisterTypeEnum.EMAIL) {
            logger.info("開始確認Email");
            result = registerService.checkEmail(input.getValue());
        } else {
            logger.info("開始確認手機");
            result = registerService.checkPhone(input.getValue());
        }
        if (result != RegisterEnum.REGISTER_SUCCESS) {
            logger.info("確認失敗");
            return result;
        }

        // 3. 確認密碼長度超過八的字且與確認密碼長短一致
        logger.info("開始確認密碼長度及相同密碼");
        result = registerService.checkPassword(input.getPassword(), input.getCheckPassword());
        logger.info("確認密碼長度及相同密碼成功");

        // 4. 確認成功, 將使用者存入DB
        if (result == RegisterEnum.REGISTER_SUCCESS) {
            registerService.saveRegister(input, type);
            if (type == RegisterTypeEnum.EMAIL) {
                mailService.sendMail(input);
            }
        }

        return result;
    }
}
