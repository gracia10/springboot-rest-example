package com.rest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.web.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String board(@RequestParam(value="idx", defaultValue="0") Long idx, Model model){
		model.addAttribute("board", boardService.findBoardByIdx(idx));
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "/board/form";
	}
	
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/board/list";
    }
}
