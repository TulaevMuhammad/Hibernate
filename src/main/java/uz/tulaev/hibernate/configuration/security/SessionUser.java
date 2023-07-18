package uz.tulaev.hibernate.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.tulaev.hibernate.configuration.repository.UserRepository;
import uz.tulaev.hibernate.model.UserPerson;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SessionUser {
    private final UserRepository userRepository;

    public UserPerson user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String name = authentication.getName();
        if (authentication.isAuthenticated()) {
            UserPerson byEmail = userRepository.findByEmail(name);
            System.out.println(byEmail);
            return byEmail;

        }
        return null;
    }

    public Long id() {
        UserPerson userPerson = user();
        if (Objects.isNull(userPerson))
            return -1L;
        return userPerson.getId();
    }
}
