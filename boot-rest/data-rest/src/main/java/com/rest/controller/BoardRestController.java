package com.rest.controller;

import org.springframework.hateoas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.domain.Board;
import com.rest.repository.BoardRepository;

@RepositoryRestController
public class BoardRestController {
	
	@Autowired
	private BoardRepository boardRepository;
	

}
