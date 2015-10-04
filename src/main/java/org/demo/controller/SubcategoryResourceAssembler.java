package org.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.demo.domain.Subcategory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
@SuppressWarnings("unused")
public class SubcategoryResourceAssembler extends ResourceAssemblerSupport<Subcategory, SubcategoryResource> {

    @Autowired
    private CategoryResourceAssembler categoryResourceAssembler;

    public SubcategoryResourceAssembler() {
        super(SubcategoryController.class, SubcategoryResource.class);
    }

    @Override
    public SubcategoryResource toResource(Subcategory subcategory) {
        SubcategoryResource resource = createResourceWithId(subcategory.getId(), subcategory);
        resource.add(linkTo(methodOn(CategoryController.class).getCategory(subcategory.getCategory().getId())).withRel("category"));
        return resource;
    }

    @Override
    protected SubcategoryResource instantiateResource(Subcategory subcategory) {
        SubcategoryResource subcategoryResource = new SubcategoryResource();
        subcategoryResource.idSubcategory = subcategory.getId();
        subcategoryResource.category = subcategory.getCategory().getId();
        subcategoryResource.name = subcategory.getName();
        subcategoryResource.record = new RecordResource(subcategory.getRecord());
        return subcategoryResource;
    }
}