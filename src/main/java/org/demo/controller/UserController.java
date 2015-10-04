package org.demo.controller;

import org.demo.domain.User;
import org.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiController.USERS_URL)
@SuppressWarnings("unused")
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	public UserResourceAssembler userResourceAssembler;

	@RequestMapping (method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserResource> getAllUsers() {
		return userResourceAssembler.toResources(userService.getAllUsers());
	}

	@RequestMapping (value = "/me",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResource whoAmI(@AuthenticationPrincipal User user) {
		return userResourceAssembler.toResource(user);
	}

}
