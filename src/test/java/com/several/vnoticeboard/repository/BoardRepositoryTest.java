package com.several.vnoticeboard.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.several.vnoticeboard.VNoticeBoardApplication;
import com.several.vnoticeboard.entity.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VNoticeBoardApplication.class)
@WebAppConfiguration
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void findByNameTest() {
		
		Board board1 = new Board(1L, "tinytos", "testpass", "Sample board text");
		boardRepository.save(board1);
		
		System.out.println(boardRepository.findByName("tinytos"));
	}
}
