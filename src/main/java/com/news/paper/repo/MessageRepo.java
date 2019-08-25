package com.news.paper.repo;

import com.news.paper.DB.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {
}
