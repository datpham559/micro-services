package datpt.spring.client;

import datpt.spring.service.GenerelMail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificationService", fallback = MailService.class)
public interface MailService {

    @PostMapping(value = "/mail/send-general-mail")
    void sendGeneralMail(@RequestBody GenerelMail generelMail);

}
