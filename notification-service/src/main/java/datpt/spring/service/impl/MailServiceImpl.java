package datpt.spring.service.impl;

import datpt.spring.service.MailService;
import datpt.spring.service.dto.GenerelMail;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendGeneralMail(GenerelMail generelMail) {
        try {
            logger.info("START... Sending email");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            //load template email with content
            Context context = new Context();
            context.setVariable("name", generelMail.getToName());
            context.setVariable("content", generelMail.getContent());
            String html = templateEngine.process("welcome-email", context);

            ///send email
            helper.setTo(generelMail.getTo());
            helper.setText(html, true);
            helper.setSubject(generelMail.getSubject());
            helper.setFrom(generelMail.getFrom());
            javaMailSender.send(message);
            logger.info("END... Email sent success");
        } catch (Exception e) {
            logger.error("Email sent with error: {}", e.getMessage());
        }
    }
}