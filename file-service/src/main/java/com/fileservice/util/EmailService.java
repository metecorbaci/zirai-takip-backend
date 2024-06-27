package com.ziraitakip.backend.util;

import com.ziraitakip.backend.constant.MailConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendActivationMail(String toMail, User user, String token) {
        try {
            String text = "Merhaba " + user.getFullName() + ", hesabınızı aktif etmek için lütfen uygulamaya aşağıdaki kodu giriniz veya link üzerinden aktivasyon sağlayabilirsiniz." +
                    "\nKOD : " + user.getActivation_code() +
                    "\nLINK : " + MailConstant.TEST_ACTIVATION_LINK +
                            "token=" + token;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(MailConstant.FROM);
            message.setTo(toMail);
            message.setSubject(MailConstant.ACTIVATION_MAIL_SUBJECT);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}