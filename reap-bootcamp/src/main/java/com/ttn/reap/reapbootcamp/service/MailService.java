package com.ttn.reap.reapbootcamp.service;
import com.ttn.reap.reapbootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    UserService userService;


    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(User user) throws MailException {
        System.out.println("#### "+user.getEmail());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom("reap@tothenew.com");
        simpleMailMessage.setSubject("Your Password");
        User userdata = userService.findUserByEmail(user.getEmail());
        System.out.println(userdata.getPassword());
        simpleMailMessage.setText("This is your Password : "+userdata.getPassword());
        System.out.println(userdata.getPassword());
        javaMailSender.send(simpleMailMessage);
    }
}
