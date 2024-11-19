package orsk.compli.service.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import orsk.compli.entities.neo4j.User;
import orsk.compli.repository.neo4j.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                user.getRoles().stream()
                        .filter(role -> role != null) // Skip null roles
                        .flatMap(role -> {
                            if (role.getPrivileges() != null) {
                                return role.getPrivileges().stream();
                            } else {
                                return Stream.empty(); // Handle null privileges
                            }
                        })
                        .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                        .collect(Collectors.toList()));
    }
}
