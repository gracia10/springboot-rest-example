package com.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rest.web.domain.Board;
import com.rest.web.domain.User;
import com.rest.web.domain.enums.BoardType;
import com.rest.web.repository.BoardRepository;
import com.rest.web.repository.UserRepository;

@SpringBootApplication
public class SpringRestSamplesApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestSamplesApplication.class, args);
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	}
	
	//@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build());

			IntStream.rangeClosed(1, 10).forEach(index ->
				boardRepository.save(Board.builder()
						.title("게시글"+index)
						.subTitle("순서"+index)
						.content("컨텐츠")
						.boardType(BoardType.free)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.user(user).build())
			);
		};
	}
}
