package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.demo.controller.RecordResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@SuppressWarnings("unused")
@JsonPropertyOrder({ "id", "email", "role", "record" })
public class UserResource extends ResourceSupport {

    @JsonProperty("id")
    public Long idUser;
    public String email;
    public String role;
    public RecordResource record;

    public UserResource() {}
}
