package org.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.demo.domain.Category;
import org.demo.domain.Subcategory;
import org.demo.service.CategoryService;
import org.demo.service.SubcategoryService;

import java.util.List;

@RestController
@RequestMapping(ApiController.SUBCATEGORIES_URL)
@SuppressWarnings("unused")
public class SubcategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    @Autowired
    private SubcategoryResourceAssembler subcategoryResourceAssembler;


    @RequestMapping (value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SubcategoryResource getSubcategory(@PathVariable Long id) throws Exception {
        return subcategoryResourceAssembler.toResource(subcategoryService.getSubcategory(id));
    }

    @RequestMapping (method = RequestMethod.GET)
    public List<SubcategoryResource> getAllCategories() {
        return subcategoryResourceAssembler.toResources(subcategoryService.getAllSubcategories());
    }

    @RequestMapping (method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubcategoryResource> createSubcategory(@RequestBody SubcategoryResource subcategoryResource) {
        Category category = categoryService.getCategory(subcategoryResource.category);
        Subcategory subcategory = subcategoryService.createSubcategory(new Subcategory(subcategoryResource.name, category));
        SubcategoryResource subcategoryResourceCreated = subcategoryResourceAssembler.toResource(subcategory);
        HttpHeaders httpHeaders = ApiHeaders.getCreatedResourceHttpHeaders(SubcategoryController.class, subcategoryResourceCreated.idSubcategory);
        return new ResponseEntity<>(subcategoryResourceCreated, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping (method = RequestMethod.PUT,
                   consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus ( HttpStatus.NO_CONTENT)
    public void updateSubcategory(@RequestBody SubcategoryResource subcategoryResource) {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(subcategoryResource.idSubcategory);
        subcategory.setName(subcategoryResource.name);
        if(subcategoryResource.category != null) {
            Category category = categoryService.getCategory(subcategoryResource.category);
            subcategory.setCategory(category);
        }
        subcategoryService.updateSubcategory(subcategory);
    }

    @RequestMapping (value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus ( HttpStatus.NO_CONTENT)
    public void deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
    }

}
