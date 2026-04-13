package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.security;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.services.AppUserService;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@NullMarked
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    public AppUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService
                .findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format("No se ha encontrado al usuario %s.", username)));

        return new AppUserDetails(appUser);

    }
}
