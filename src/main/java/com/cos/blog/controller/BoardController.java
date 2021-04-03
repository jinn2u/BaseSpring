package com.cos.blog.controller;

import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pabelable){
        model.addAttribute("boards", boardService.writingList(pabelable));
        return "index";
    }

    @GetMapping("/board/saveForm")
    public  String saveForm(){
        return "board/saveForm";
    }
}
