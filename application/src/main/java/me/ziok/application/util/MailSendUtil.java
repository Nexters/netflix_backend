package me.ziok.application.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSendUtil {

    private String user ="#";
    private String password = "#";

    @Autowired
    JavaMailSenderImpl mailSender;//보내는 계정의 정보가 들어가야함.

    public void sendMail(String userEmail, String code){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", true); //통신에 사용되는 데이터를 암호화


        JavaMailSender mailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) mailSender).setPort(465); //gmail사용 시 STMP 서버와 통신하는 포트 번호
        ((JavaMailSenderImpl) mailSender).setUsername(user);
        ((JavaMailSenderImpl) mailSender).setPassword(password);
        ((JavaMailSenderImpl) mailSender).setJavaMailProperties(properties);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
                helper.setFrom(user);
                helper.setTo(userEmail);
                helper.setSubject("[netflixshare] 비밀번호변경 인증코드 발송 안내 메일입니다.");
                helper.setText("인증 코드 : <"+code+">를 입력해주세요.");
            }
        };

        mailSender.send(preparator);

    }
}
