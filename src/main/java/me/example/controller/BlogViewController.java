package me.example.controller;

import lombok.RequiredArgsConstructor;
import me.article.domain.Article;
import me.article.service.BlogService;
import me.example.dto.ArticleListViewResponse;
import me.example.dto.ArticleViewResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장
        return "articleList"; // articleList.html라는 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        // 화면에서 사용할 모델에 데이터를 저장한 다음, 보여줄 화면의 템플릿 이름을 반환합니다.
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else { // 3 id가 없으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}