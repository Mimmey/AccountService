package account.config;

import account.business.entities.dbentities.Group;
import account.business.entities.dbentities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> rolesAndAuthorities;

    public UserDetailsImpl(User user) {
        this.username = user.getEmail().toLowerCase(Locale.ROOT);
        this.password = user.getPassword();
        this.rolesAndAuthorities = getAuthorities(user);
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        List<Group> userGroups = user.getRoles();
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Group userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getName().toUpperCase()));
        }

        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAndAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
