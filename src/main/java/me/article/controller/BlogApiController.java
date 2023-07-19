package me.article.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.article.domain.Article;
import me.article.dto.AddArticleRequest;
import me.article.dto.ArticleResponse;
import me.article.dto.UpdateArticleRequest;
import me.article.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

  private final BlogService blogService;

  //요청이 오면 응답용 객체인 ArticleResponse에 파싱해 body에 담아 클라이언트에게 전송
  @GetMapping("/api/articles")
  public ResponseEntity<List<ArticleResponse>> findAllArticles() {
    List<ArticleResponse> articles = blogService.findAll()
        .stream()
        .map(ArticleResponse::new)
        .toList();
    //스트림사용
    return ResponseEntity.ok()
        .body(articles);
  }

  @GetMapping("/api/articles/{id}")
  //URL경로에서 값 추출
  public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
    Article article = blogService.findById(id);

    return ResponseEntity.ok()
        .body(new ArticleResponse(article));
  }

  @PostMapping("/api/articles")
  public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
    Article saveArticle = blogService.save(request);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(saveArticle);
  }

  @PutMapping("/api/articles/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable long id,
      @RequestBody UpdateArticleRequest request) {
    Article updatedArticle = blogService.update(id, request);
    return ResponseEntity.ok()
        .body(updatedArticle);
  }


  @DeleteMapping("/api/articles/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
    blogService.delete(id);
    return ResponseEntity.ok()
        .build();
  }
}
