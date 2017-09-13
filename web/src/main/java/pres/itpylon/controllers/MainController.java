package pres.itpylon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pres.itpylon.domain.Classified;
import pres.itpylon.repository.ArticleRepository;
import pres.itpylon.repository.ClassifiedRepository;

import java.util.*;

/**
 * Author sunyulong
 * Date Created on 2017/9/8 15:36
 */

@RestController
@RequestMapping("/")
public class MainController {

    private final ClassifiedRepository classifiedRepository;

    private final ArticleRepository articleRepository;

    @Autowired
    public MainController(ClassifiedRepository classifiedRepository, ArticleRepository articleRepository) {
        this.classifiedRepository = classifiedRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("classified", classifiedRepository.findAllByFather(0));

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        model.addAttribute("alist", articleRepository.findAll(sort));
        List<Classified> breads = new ArrayList<Classified>(1);
        breads.add(classifiedRepository.findOne(0));
        model.addAttribute("breads", breads);
        return new ModelAndView("page/index", "mav", model);
    }

    @GetMapping(value = "{clazz}")
    public ModelAndView findByKind(@PathVariable(value = "clazz") String clazz, Model model) {
        Classified classified = classifiedRepository.getByTitle(clazz);
        model.addAttribute("alist", articleRepository.findAllByKind(classified.getId()));
        model.addAttribute("classified", classifiedRepository.findAllByFather(0));
        List<Classified> breads = new ArrayList<Classified>(4);
        breads.add(classified);
        while (classified.getId() != 0) {
            classified = classifiedRepository.findOne(classified.getFather());
            breads.add(classified);
        }
        Collections.reverse(breads);
        model.addAttribute("breads", breads);
        return new ModelAndView("page/index", "mav", model);
    }

}
