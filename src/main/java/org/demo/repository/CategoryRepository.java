package org.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.demo.domain.Category;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query ("select c from Category c order by c.name")
    List<Category> findAllOrderByName();

    Category findByName(String name);

}
