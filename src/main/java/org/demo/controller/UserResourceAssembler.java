package org.demo.controller;

import org.demo.domain.Role;
import org.demo.domain.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@SuppressWarnings("unused")
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        return createResourceWithId(user.getId(), user);
    }

    @Override
    protected UserResource instantiateResource(User user) {
        UserResource userResource = new UserResource();
        userResource.idUser = user.getId();
        userResource.email = user.getEmail();
        userResource.role = user.getRole().getAuthority();
        userResource.record = new RecordResource(user.getRecord());
        return userResource;
    }
}
