package com.example.yung_han_prj.domain.posts;

import com.example.yung_han_prj.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @NoArgsConstructor
    @Entity
    public class Posts extends BaseTimeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 500, nullable = false)
        private String title;

        @Column(columnDefinition = "TEXT", nullable = false)
        private String content;

        private String author;

        @Builder
        public Posts(String title, String content, String author){
            this.title = title;
            this.content = content;
            this.author = author;
        }

        /*
        * 영속성 컨텍스트로 인하여 자동 업데이트
        * */
        public void update(String title, String content){
            this.title = title;
            this.content = content;
        }

    }

