package org.demo.controller;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.demo.domain.Category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
@SuppressWarnings("unused")
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    public CategoryResourceAssembler() {
        super(CategoryController.class, CategoryResource.class);
    }

    @Override
    public CategoryResource toResource(Category category) {
        CategoryResource resource = createResourceWithId(category.getId(), category);
        resource.add(linkTo(methodOn(CategoryController.class).getAllSubcategories(category.getId())).withRel("subcategories"));
        return resource;
    }

    @Override
    protected CategoryResource instantiateResource(Category category) {
        CategoryResource categoryResource = new CategoryResource();
        categoryResource.idCategory = category.getId();
        categoryResource.name = category.getName();
        categoryResource.record = new RecordResource(category.getRecord());
        return categoryResource;
    }
}
