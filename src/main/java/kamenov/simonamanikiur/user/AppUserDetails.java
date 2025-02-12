package kamenov.cupcakespakoandmoni.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class AppUserDetails extends User {

    private final UUID uuid;
    private String fullName;

    public AppUserDetails(String username, String password,
                          Collection<? extends GrantedAuthority> authorities, UUID uuid) {
        super(username, password, authorities);
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public AppUserDetails setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
