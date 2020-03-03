package com.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.domain.Board;
import com.rest.repository.BoardRepository;

@RepositoryRestController
public class BoardRestController {
	
	@Autowired
	private BoardRepository boardRepository;
	
    @GetMapping("/boards")
    public @ResponseBody CollectionModel<Board> simpleBoard(@PageableDefault Pageable pageable) {
    	Page<Board> boardList = boardRepository.findAll(pageable);
    	
    	PageMetadata pageMetadata = new PageMetadata(boardList.getSize(), boardList.getNumber(), boardList.getTotalElements());
    	PagedModel<Board> resources = new PagedModel<Board>(boardList.getContent(), pageMetadata);
    	resources.add(linkTo(methodOn(BoardRestController.class).simpleBoard(pageable)).withSelfRel());
        return resources;
    }
}
