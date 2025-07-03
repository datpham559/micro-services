package datpt.spring.client.impl;

import datpt.spring.client.MailService;
import datpt.spring.service.GenerelMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendGeneralMail(GenerelMail generelMail) {
        logger.error("Something went wrong: {}", generelMail);
    }
}
