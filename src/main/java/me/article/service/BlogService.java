package me.article.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.article.domain.Article;
import me.article.dto.AddArticleRequest;
import me.article.dto.UpdateArticleRequest;
import me.article.repository.BlogRepository;
import me.utils.error.exception.ArticleNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service //빈으로 등록
@EnableAsync // 비동기처리
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
        //AddArticleRequest 에 저장된 값들을 article 데이터베이스에 저
    }


    @Cacheable(value = "article") // 데이터를 가져와서 캐쉬에 key값으로 저장해줌
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    public void delete(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " +
                        id));
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article);
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
