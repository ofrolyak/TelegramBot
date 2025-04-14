package com.tatko.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class AuthoritiesConverter implements
        Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * Convert Jwt to GrantedAuthority's Collection.
     * @param source
     * @return GrantedAuthority's Collection.
     */
    @Override
    public Collection<GrantedAuthority> convert(final Jwt source) {

        log.debug("Converting Jwt {}", source);

        Map<String, Object> realmAccess
                = (Map<String, Object>) source.getClaims().get("realm_access");
        if (Objects.nonNull(realmAccess) && realmAccess.containsKey("roles")) {
            Collection<GrantedAuthority> roles
                    = ((Collection<String>) realmAccess.get("roles")).stream()
                    .map(role -> "ROLE_" + role)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            log.debug("Realm access role info found: {}", roles);
            return roles;
        }
        log.debug("No realm access role info found");
        return Collections.emptyList();
    }
}
