package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.demo.controller.RecordResource;
import org.springframework.hateoas.ResourceSupport;

@SuppressWarnings("unused")
@JsonPropertyOrder({ "id", "category", "name", "record" })
public class SubcategoryResource extends ResourceSupport {

    @JsonProperty("id")
    public Long idSubcategory;
    public Long category;
    public String name;
    public RecordResource record;

    public SubcategoryResource() {}

}
