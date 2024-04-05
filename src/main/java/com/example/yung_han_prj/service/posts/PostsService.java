package com.example.yung_han_prj.service.posts;

import com.example.yung_han_prj.domain.posts.Posts;
import com.example.yung_han_prj.domain.posts.PostsRepository;
import com.example.yung_han_prj.web.dto.PostsResponseDto;
import com.example.yung_han_prj.web.dto.PostsSaveRequestDto;
import com.example.yung_han_prj.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        /*
        * 영속성 컨텍스트로 업데이트 처리하기!
        * */
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

}
