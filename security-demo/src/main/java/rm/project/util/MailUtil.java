package rm.project.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 信件Util
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public class MailUtil {

    static JavaMailSenderImpl mailSender;

    /**
     * 一般的發送mail準備, 可以在application.yml內調整對應的mail server的host以及port
     * 其中寄件者要mail server的帳號及所對應的密碼
     */
    static {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(PropertiesExtractor.getProperty("amazingtalker.mail.host"));
        mailSender.setPort(PropertiesExtractor.getProperty("amazingtalker.mail.port"));

        mailSender.setUsername(PropertiesExtractor.getProperty("amazingtalker.mail.sender-name"));
        mailSender.setPassword(PropertiesExtractor.getProperty("amazingtalker.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", PropertiesExtractor.getProperty("amazingtalker.mail.host"));
    }

    /**
     * 發送郵件
     *
     * @param from 寄件人信箱
     * @param to 收件人信箱
     * @param subject 主旨
     * @param text 內容
     */
    public static void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
//        mailSender.send(message);
    }
}
