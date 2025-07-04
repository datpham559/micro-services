package datpt.spring.client.fallback;

import datpt.spring.client.MailService;
import datpt.spring.service.dto.GeneralMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MailFallBackFactory implements FallbackFactory<MailService> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public MailService create(Throwable cause) {
        return new MailService() {

            @Override
            public Mono<Void> sendGeneralMail(GeneralMail generalMail) {
                logger.error("Something went wrong with param {} in sendGeneralMail: {}", generalMail, cause.getMessage());
                return Mono.empty();
            }
        };
    }
}
