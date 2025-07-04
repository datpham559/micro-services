package datpt.spring.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "notificationService")
public interface MailService {

}
