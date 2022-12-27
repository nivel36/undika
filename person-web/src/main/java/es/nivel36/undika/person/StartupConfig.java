package es.nivel36.undika.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;

@ApplicationScoped
@FacesConfig
public class StartupConfig {
	
	Logger log = LoggerFactory.getLogger(StartupConfig.class);
	
	@PostConstruct
	public void init() {
		log.info("Startup of Undika");
	}	 

}