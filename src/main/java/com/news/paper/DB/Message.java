package com.news.paper.DB;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    public Message(String text, LocalDateTime creationDate) {
//        this.text = text;
//        this.creationDate = creationDate;
//    }

    private String text;


    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

}
