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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PictureRepository pictureRepository;

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
    
    @PostConstruct
    public void initializeRepositories() throws IOException {
    	byte[] picture1 = Files.readAllBytes(Paths.get("src/main/resources/static/mannakuva.jpg"));
    	byte[] picture2 = Files.readAllBytes(Paths.get("src/main/resources/static/emmikuva.jpg"));
    	byte[] picture3 = Files.readAllBytes(Paths.get("src/main/resources/static/tillikuva.jpg"));
    	for(int i = 0; i < 4; i++){
    		pictureRepository.save(new Picture(picture1, "Manna ratsastaa ponilla"));
    		pictureRepository.save(new Picture(picture2, "Emmi ratsastaa ponilla"));
    		pictureRepository.save(new Picture(picture3, "Tilli ratsastaa ponilla"));
    	}
    }
}
