package rm.project.service;

import rm.project.restful.input.RegisterInput;
import rm.project.util.MailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 信件Service
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@Service
public class MailService {

    @Value("${amazingtalker.mail.register-success-content:%s 您好! 歡迎加入 AmazingTalker}")
    private String registerSuccessContent;
    @Value("${amazingtalker.mail.sender-name}")
    private String from;

    /**
     * 將RegisterInput轉變成信件模式寄發給收件人
     * @param input　輸入參數
     */
    public void sendMail(RegisterInput input) {
        String to = input.getValue();
        String subject = String.format(registerSuccessContent, input.getName());
        String text = subject;
        MailUtil.send(from, to , subject, text);
    }
}
