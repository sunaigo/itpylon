package pres.itpylon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pres.itpylon.domain.Article;

import java.util.List;

/**
 * Author sunyulong
 * Date Created on 2017/9/8 15:25
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    Article findOne(Long id);


    List<Article> findAll();

    List<Article> findAllByKind(int kind);

    Article save(Article article);
}
