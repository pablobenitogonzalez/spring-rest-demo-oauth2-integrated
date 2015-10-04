package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

@SuppressWarnings("unused")
@JsonPropertyOrder({ "id", "name", "record" })
public class CategoryResource extends ResourceSupport {

    @JsonProperty("id")
    public Long idCategory;
    public String name;
    public RecordResource record;

    public CategoryResource() {}
}
