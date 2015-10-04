package org.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.demo.domain.Category;
import org.demo.service.CategoryService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(ApiController.CATEGORIES_URL)
@SuppressWarnings("unused")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    @Autowired
    private SubcategoryResourceAssembler subcategoryResourceAssembler;


    @RequestMapping (value = "/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryResource getCategory(@PathVariable Long id) {
        return categoryResourceAssembler.toResource(categoryService.getCategory(id));
    }

    @RequestMapping (method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryResource> getAllCategories() {
        return categoryResourceAssembler.toResources(categoryService.getAllCategories());
    }

    @RequestMapping (value = "/{id}/subcategories",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubcategoryResource> getAllSubcategories(@PathVariable Long id) {
        return subcategoryResourceAssembler.toResources(categoryService.getCategory(id).getSubcategories());
    }

    @RequestMapping (method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResource> createCategory(@RequestBody CategoryResource categoryResource) {
        Category category = categoryService.createCategory(new Category(categoryResource.name));
        CategoryResource categoryResourceCreated = categoryResourceAssembler.toResource(category);
        HttpHeaders httpHeaders = ApiHeaders.getCreatedResourceHttpHeaders(CategoryController.class, categoryResourceCreated.idCategory);
        return new ResponseEntity<>(categoryResourceCreated, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping (method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus ( HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody CategoryResource categoryResource) {
        Category category = new Category();
        category.setId(categoryResource.idCategory);
        category.setName(categoryResource.name);
        categoryService.updateCategory(category);
    }

    @RequestMapping (value = "/{id}",
                    method = RequestMethod.DELETE)
    @ResponseStatus ( HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
