package fr.myhome.server.tools;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.myhome.server.MyHomeServerApplication;

@Component
public class ApplicationInformations {

    protected final Logger logger = LoggerFactory.getLogger(MyHomeServerApplication.class);

    @Value("${application.version:unknown}")
    String version;
    
    @PostConstruct
    public void setUpApplicationInformations() {
        this.logger.info("INFORMATION - Application version : " + version);
    }

}
