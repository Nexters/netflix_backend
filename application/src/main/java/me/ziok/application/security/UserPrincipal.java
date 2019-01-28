package me.ziok.application.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ziok.application.model.Account;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@RequiredArgsConstructor
@Getter
@Setter
public class UserPrincipal implements OAuth2User, UserDetails {


    @NonNull
    private Long id;

    private String name;

    @NonNull
    private String email;

    @NonNull
    @JsonIgnore
    private String password;

    @NonNull
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public static UserPrincipal create(Account account) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));


        return new UserPrincipal(
                account.getId(),
                account.getEmail(),
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPrincipal that = (UserPrincipal) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}
