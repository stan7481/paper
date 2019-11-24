package com.news.paper.repo;

import com.news.paper.DB.Message;
import com.news.paper.DB.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends JpaRepository<Message, Integer> {

    List<Message> findByAuthor(User user);

//    @EntityGraph(attributePaths = {"likes"})
//    Optional<User> findById(String s);






}
