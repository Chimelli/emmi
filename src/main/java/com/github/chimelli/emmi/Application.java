package com.github.chimelli.emmi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
	

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    
    @Configuration
    @Profile("development")
    public static class DevelopmentInitializer {
    	
    	@Autowired
    	private CommentRepository commentRepository;
    	
    	@Autowired
    	private PictureRepository pictureRepository;
    	
	    @PostConstruct
	    public void initializeRepositories() throws IOException {
	    	byte[] picture1 = Files.readAllBytes(Paths.get("src/main/resources/static/mannakuva.jpg"));
	    	byte[] picture2 = Files.readAllBytes(Paths.get("src/main/resources/static/emmikuva.jpg"));
	    	byte[] picture3 = Files.readAllBytes(Paths.get("src/main/resources/static/tillikuva.jpg"));
	    	for(int i = 0; i < 4; i++) {
	    		Picture newPicture = new Picture(picture1, "Manna ratsastaa ponilla");
	    		pictureRepository.save(newPicture);
	    		pictureRepository.save(new Picture(picture2, "Emmi ratsastaa ponilla"));
	    		pictureRepository.save(new Picture(picture3, "Tilli ratsastaa ponilla"));
		    	for(int o = 0; o < 20; o++) {
		    		commentRepository.save(new Comment(newPicture, "Laura", "Söpö kuva!"));
		    		commentRepository.save(new Comment(newPicture, "Manna", "Jep!"));
		    		commentRepository.save(new Comment(newPicture, "Toni", "Terveisiä kuvan ottajalle! Terveisiä myös sivun tekijälle."));
		    		commentRepository.save(new Comment(newPicture, "Emmi", "Minä tykkään Mannasta vain vähän. Isistä ja Tillistä tykkään tosi paljon, mutta äitistäkin tykkään vain vähän. Kaikki tykkää Emmistä. Emmi tykkää syksystä ja talvesta ja keväästä ja kesästä."));
		    		commentRepository.save(new Comment(newPicture, "Tilli", "apuaaaa"));
		    	}
	    	}
	    }
    }
}
