package datpt.spring.client;

import datpt.spring.client.fallback.MailFallBackFactory;
import datpt.spring.service.dto.GeneralMail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@FeignClient(name = "notificationService", fallback = MailFallBackFactory.class)
public interface MailService {

    @PostMapping(value = "notification/mail/send-general-mail")
    Mono<Void> sendGeneralMail(@RequestBody GeneralMail generalMail);

}
