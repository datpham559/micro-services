package datpt.spring.resource;

import datpt.spring.service.MailService;
import datpt.spring.service.dto.GenerelMail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping(value = "/mail/send-general-mail")
    public void sendGeneralMail(@RequestBody GenerelMail generelMail) {
        mailService.sendGeneralMail(generelMail);
    }
}
