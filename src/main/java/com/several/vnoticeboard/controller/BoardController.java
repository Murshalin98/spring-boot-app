package com.several.vnoticeboard.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.several.vnoticeboard.dto.RegistrationForm;
import com.several.vnoticeboard.entity.Board;
import com.several.vnoticeboard.repository.BoardRepository;

@Controller
public class BoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardRepository boardRepository;

	// GET Request for view Single board
	@RequestMapping(value = "/{boardName}", method = RequestMethod.GET)
	public String showBoard(@PathVariable("boardName") String boardName, Model model){
		LOGGER.debug("Rendering Board page: "+ boardName);
		
		Board board = boardRepository.findByName(boardName);
		
		if (board == null) {
			LOGGER.debug("Board not found: "+ boardName);
			
			model.addAttribute("boardName", boardName);
			return "newboard";
		}
		
		model.addAttribute("schoolName", boardName);
		model.addAttribute("boardContent", board.getContent());
		return "board";
	}
	
	
	// POST Request for Board Creation
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerBoard(@Valid RegistrationForm registrationForm,
			BindingResult result, WebRequest request, RedirectAttributes redirectArrtibutes) {
		
		System.out.println(request.getParameter("boardName"));
		System.out.println(request.getParameter("password"));
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			
			redirectArrtibutes.addAttribute("error", "Password should be more tham 3 characters and less than 100");
			return "redirect:/"+ request.getParameter("boardName");
		}
		
		
		Board board = new Board();
		board.setName(request.getParameter("boardName"));
		board.setPassword(request.getParameter("password"));
		
		boardRepository.save(board);
		return "redirect:/"+ request.getParameter("boardName");
	}
	
	
	// GET Request for Edit
	@RequestMapping(value = "/{boardName}/edit", method = RequestMethod.GET)
	public String editBoard(@PathVariable("boardName") String boardName, Model model){
		LOGGER.debug("Rendering Board edit page: "+ boardName);
		
		Board board = boardRepository.findByName(boardName);
		
		if (board == null) {
			LOGGER.debug("Board not found: "+ boardName);
			
			model.addAttribute("boardName", boardName);
			return "home";
		}
		
		model.addAttribute("content", board.getContent());
		
		return "editboard";
	}
	
	
	// POST Request for Board Updating
	@RequestMapping(value = "/{boardName}/edit", method = RequestMethod.POST)
	public String updateBoard(@PathVariable("boardName") String boardName,
			WebRequest request, RedirectAttributes redirectArrtibutes, Model model) {
		
		Board board = boardRepository.findByName(boardName);
		
		if (board == null) {
			LOGGER.debug("Board not found: "+ boardName);

			return "home";
		}
		
		if (board.getPassword().equals(request.getParameter("password"))) {
			board.setContent(request.getParameter("content"));
			boardRepository.save(board);
			
			return "redirect:/"+ request.getParameter("boardName");
		}
		
		boardRepository.save(board);
		return "redirect:/"+ request.getParameter("boardName") + "/edit";
	}
}
