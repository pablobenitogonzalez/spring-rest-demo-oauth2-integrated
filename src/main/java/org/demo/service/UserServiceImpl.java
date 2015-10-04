package org.demo.service;

import org.demo.domain.Role;
import org.demo.domain.User;
import org.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username.toUpperCase());
		if (user == null) {
			throw new UsernameNotFoundException(messageService.getMessage("message.user.not.found", new Object[]{username}));
		}
		return new UserRepositoryUserDetails(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	private final static class UserRepositoryUserDetails extends User implements UserDetails {

		private UserRepositoryUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getRoles();
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return getFails() < 3;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return getEnabled();
		}

	}

}
