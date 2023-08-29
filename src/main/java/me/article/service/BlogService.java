package me.article.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.article.domain.Article;
import me.article.dto.AddArticleRequest;
import me.article.dto.UpdateArticleRequest;
import me.article.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@RequiredArgsConstructor //final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
        //AddArticleRequest 에 저장된 값들을 article 데이터베이스에 저
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found : " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드 엔티티의 필드값이 바뀌면 중간에 에러가 발생해도 제대로 된 값 수정을 보장
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not Fount : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().
                getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
