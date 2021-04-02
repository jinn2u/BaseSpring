package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    // 회원가입
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        System.out.println(board.getTitle()+" "+ board.getContent());
        boardService.writing(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
