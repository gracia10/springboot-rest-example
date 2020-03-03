package com.rest;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rest.domain.Board;
import com.rest.domain.User;
import com.rest.domain.enums.BoardType;
import com.rest.repository.BoardRepository;
import com.rest.repository.UserRepository;

@SpringBootApplication
public class RestWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebApplication.class, args);
	}

//	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args)->{
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build()
			);
			
			IntStream.rangeClosed(1, 5).forEach(index ->
				boardRepository.save(Board.builder()
						.title("게시글"+index)
						.subTitle("순서"+index)
						.content("콘텐츠")
						.boardType(BoardType.free)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.user(user).build())
			);
		};
	}
}
