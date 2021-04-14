package com.cos.blog.Repository;

import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
}
