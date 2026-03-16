package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.security;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NullMarked
public class AppUserDetails implements UserDetails {

    @Getter
    private final AppUser appUser;

    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.appUser
                .getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(String.format("ROLE_%s", rol.getName())))
                .toList();
    }

    @Override
    public @Nullable String getPassword() {
        return this.appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUser.getEmail();
    }
}
