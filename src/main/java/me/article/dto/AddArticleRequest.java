package me.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.article.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

  private String title;
  private String content;

  // 빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메소드
  public Article toEntity(String author) {
    return Article.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
  }
}
