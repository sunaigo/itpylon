package pres.itpylon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pres.itpylon.entity.Article;
import pres.itpylon.entity.Classified;
import pres.itpylon.repository.ArticleRepository;
import pres.itpylon.repository.ClassifiedRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author sunyulong
 * Date Created on 2017/9/8 15:51
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ClassifiedRepository classifiedRepository;

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ClassifiedRepository classifiedRepository, ArticleRepository articleRepository) {
        this.classifiedRepository = classifiedRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/{id}")
    public ModelAndView article(@PathVariable(value = "id") Long id, Model model) {
        Article article = articleRepository.findOne(id);
        model.addAttribute("article", article);
        model.addAttribute("classified", classifiedRepository.findAllByFather(0));
        Classified classified = classifiedRepository.findOne(article.getKind());
        List<Classified> breads = new ArrayList<Classified>(4);
        breads.add(classified);
        while (classified.getId() != 0) {
            classified = classifiedRepository.findOne(classified.getFather());
            breads.add(classified);
        }
        Collections.reverse(breads);
        model.addAttribute("breads", breads);
        return new ModelAndView("page/article", "mav", model);
    }

    @GetMapping(value = "/write")
    public ModelAndView write(){
        return new ModelAndView("page/writer");
    }

    @PostMapping(value = "/saveArticle")
    public ModelAndView saveArticle(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "md") String md,
            Model model
    ){
        Article article = new Article();
        article.setTitle(title);
        article.setBody(md);
        article.setAuthor("pylon");
        article.setKind(2);
        article.setKindName("Spark");
        articleRepository.save(article);
        return new ModelAndView("redirect:/");
    }

}
