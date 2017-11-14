package pres.itpylon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.itpylon.entity.Classified;

import java.util.List;

/**
 * Author sunyulong
 * Date Created on 2017/9/8 15:30
 */
public interface ClassifiedRepository extends JpaRepository<Classified, Integer> {

    List<Classified> findAllByFather(int father);

    @Override
    Classified findOne(Integer integer);

    Classified getByTitle(String title);

    Classified save(Classified classified);
}
