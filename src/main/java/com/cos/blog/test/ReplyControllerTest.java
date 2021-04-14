package com.cos.blog.test;

import com.cos.blog.Repository.BoardRepository;
import com.cos.blog.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ReplyControllerTest {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id){
        return boardRepository.findById(id).get();
    }
}
