package com.example.replication.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplTest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String contents;

    public static ReplTest of(String contents){
        return ReplTest.builder()
                .contents(contents)
                .build();
    }

}
