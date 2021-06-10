package tk.mwacha.configuration;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mwacha.entities.User;
import tk.mwacha.service.UserService;


@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPass(),
                true,
                true,
                true,
                false,
                getAuthorities(user));

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final User user) {
        return Stream.of(new SimpleGrantedAuthority(user.getProfile().getProfileName())).collect(
            Collectors.toList());
    }
}
