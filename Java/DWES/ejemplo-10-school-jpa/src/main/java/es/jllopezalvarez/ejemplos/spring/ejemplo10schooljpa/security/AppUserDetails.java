package es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.security;

import es.jllopezalvarez.ejemplos.spring.ejemplo10schooljpa.entities.AppUser;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NullMarked
public class AppUserDetails implements UserDetails {

    private final AppUser appUser;

    public AppUserDetails(AppUser appUser){
        this.appUser = appUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return this.appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUser.getEmail();
    }

    public String getFullName(){
        return String.join(" ", this.appUser.getFirstName(), this.appUser.getLastName());
    }
}
