package kamenov.cupcakespakoandmoni.models.user;

import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.models.enums.UserRoleEnum;
import kamenov.cupcakespakoandmoni.repos.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSession {

    private  final UserRepository userRepository;

    public UserSession(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean isLogged() {
        return hasRole(UserRoleEnum.USER);
    }

    public boolean isAdmin() {
        return hasRole(UserRoleEnum.ADMIN);
    }
    public boolean isOnlyUser() {
        return getAuthentication().getAuthorities().stream()
                .allMatch(role -> role.getAuthority().equals("ROLE_" + UserRoleEnum.USER));
    }

    public String getUsername() {
        return getUserDetails().getUsername();
    }

    public boolean hasRole(UserRoleEnum userRoles) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_" + userRoles));
    }

    private UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public UserEntity get(){
        String username = getUsername();
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " was not found!"));

    }

}
