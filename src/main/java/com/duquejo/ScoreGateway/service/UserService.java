package com.duquejo.ScoreGateway.service;

import com.duquejo.ScoreGateway.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

  private static final Log logger = LogFactory.getLog(UserService.class);

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.duquejo.ScoreGateway.entity.User user = userRepository.findByUsername(username);
    return new User(
        user.getUsername(),
        user.getPassword(),
        user.isActive(),
        user.isActive(),
        user.isActive(),
        user.isActive(),
        buildGranted(user.getRole())
    );
  }

  public List<GrantedAuthority> buildGranted(byte role) {
    String[] roles = { "ANONYMOUS", "USER", "ADMINISTRATOR" };
    List<GrantedAuthority> auths = new ArrayList<>();
    for (int i = 0; i < role; i++) {
      auths.add(new SimpleGrantedAuthority(roles[i]));
    }
    return auths;
  }
}
