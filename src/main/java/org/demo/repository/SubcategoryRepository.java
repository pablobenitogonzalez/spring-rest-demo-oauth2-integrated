package org.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.demo.domain.Subcategory;

import java.util.List;

@SuppressWarnings("unused")
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @Query ("select s from Subcategory s order by s.name")
    List<Subcategory> findAllOrderByName();

    Subcategory findByNameAndCategory_Id(String name, Long id);

}
