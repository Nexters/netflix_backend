package me.ziok.application.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ziok.application.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class UserPrincipal implements OAuth2User, UserDetails {


    @NonNull
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static UserPrincipal create(Account account) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        System.out.println("is it running? user principal");

        return new UserPrincipal(
                account.getId(),
                account.getAccountId(),
                account.getPassword(),
                authorities
        );
    }

    public static UserPrincipal create(Account account, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(account);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }



    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
