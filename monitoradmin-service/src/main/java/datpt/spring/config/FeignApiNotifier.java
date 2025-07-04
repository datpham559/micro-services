package datpt.spring.config;

import datpt.spring.client.MailService;
import datpt.spring.service.GeneralMail;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class FeignApiNotifier extends AbstractEventNotifier {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MailService mailService;

    protected FeignApiNotifier(InstanceRepository repository, MailService mailService) {
        super(repository);
        this.mailService = mailService;
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        if (event instanceof InstanceStatusChangedEvent statusEvent) {
            GeneralMail generalMail = getGeneralMail(instance, statusEvent);
            if (generalMail != null) {
                return Mono.fromRunnable(() -> {
                    try {
                        mailService.sendGeneralMail(generalMail);
                    } catch (Exception e) {
                        logger.error("Error notifying: {}", e.getMessage());
                    }
                }).subscribeOn(Schedulers.boundedElastic()).then();
            }
        }
        return Mono.empty();
    }

    private static GeneralMail getGeneralMail(Instance instance, InstanceStatusChangedEvent statusEvent) {
        String status = statusEvent.getStatusInfo().getStatus();
        String service = instance.getRegistration().getName();
        if ("OFFLINE".equals(status)) {
            GeneralMail generalMail = new GeneralMail(
                    "datpham559@gmail.com",
                    "nhoccodon1711@gmail.com",
                    "Nguyen Van A",
                    "Thông báo hệ thống",
                    "Dịch vụ" + service + "hiện đang không phản hồi. Vui lòng kiểm tra."
            );
            return generalMail;
        }
        return null;
    }
}
