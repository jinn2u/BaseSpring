package com.cos.blog.controller;

import com.cos.blog.Repository.BoardRepository;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    // 글 상세보기
    @GetMapping("/board/{id}")
    public String writeDetails(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.writeDetails(id));
        return "board/detail";
    }
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        System.out.println(id);
        System.out.println("8773");
        model.addAttribute("board", boardService.writeDetails(id));
        return "board/updateForm";
    }
}
