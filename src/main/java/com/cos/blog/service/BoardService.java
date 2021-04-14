package com.cos.blog.service;

import com.cos.blog.Repository.BoardRepository;
import com.cos.blog.Repository.ReplyRepository;
import com.cos.blog.Repository.UserRepository;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void writing(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> writingList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board writeDetails(int id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void writeDelete(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void writeEdit(int id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
        });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void writeReply(ReplySaveRequestDto replySaveRequestDto){
        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("can't find User.");
        });
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
            return new IllegalArgumentException("댓글쓰기 실패: 게시글아이디를 찾을 수 없습니다.");
        });
        Reply reply = Reply.builder()
            .user(user)
            .board(board)
            .content(replySaveRequestDto.getContent())
            .build();

        replyRepository.save(reply);
    }
}
