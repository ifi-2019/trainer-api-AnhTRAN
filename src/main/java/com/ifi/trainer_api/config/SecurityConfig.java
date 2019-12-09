package com.ifi.trainer_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/** Attention : Cette classe sert à désactiver CSRF (security Synchronizer Token Pattern)
 * Ne désactivez cette sécurité uniquement si votre API n’est pas accessible directement !
 * Attention, ne faites pas ça en entreprise ou sur vos projets perso sans la validation d’un responsable sécurité !
 * */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}