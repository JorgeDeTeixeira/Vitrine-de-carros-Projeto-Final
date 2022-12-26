package vitrine.marca.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vitrine.marca.models.UserModel;
import vitrine.marca.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));
		return new User(userModel.getUsername(), userModel.getPassword(), true, true, true, true, userModel.getAuthorities());
	}

}
