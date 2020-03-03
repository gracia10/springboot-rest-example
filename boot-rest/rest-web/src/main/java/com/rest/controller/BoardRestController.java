package com.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.domain.Board;
import com.rest.repository.BoardRepository;

//@RestController
//@RequestMapping("/api/boards")
public class BoardRestController {
	
	@Autowired
	private BoardRepository boardRepository;
	

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable) {
        
    	Page<Board> boards = boardRepository.findAll(pageable);
    	PageMetadata pageMetadata = new PageMetadata(boards.getSize(), boards.getNumber(), boards.getTotalElements());
    	PagedModel<Board> resources = new PagedModel<Board>(boards.getContent(), pageMetadata);
    	resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(resources);
    }

//    @GetMapping(value = "/{id}")
//    public Foo findById(@PathVariable("id") Long id) {
//        return RestPreconditions.checkFound(service.findById(id));
//    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postBoard(@RequestBody Board board){
    	board.setCreatedDateNow();
    	boardRepository.save(board);
    }
    
    @PutMapping("/{idx}")
    @ResponseStatus(HttpStatus.OK)
    public void putBoard(@PathVariable Long idx,@RequestBody Board board){
    	board.update(idx);
    	boardRepository.save(board);
    }
    
    @DeleteMapping("/{idx}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long idx){
    	boardRepository.deleteById(idx);
    }
	
}
