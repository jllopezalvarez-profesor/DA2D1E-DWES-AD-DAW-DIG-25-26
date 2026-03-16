package es.jllopezalvarez.ejemplos.ejemplo20issuetracker.web.controllers;

import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.common.entities.AppUser;
import es.jllopezalvarez.ejemplos.ejemplo20issuetracker.security.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute(name="currentAppUser")
    public AppUser currentAppUser(@AuthenticationPrincipal AppUserDetails appUserDetails){
        if (appUserDetails == null) {
            return null;
        }
        return appUserDetails.getAppUser();
    }
}
