package com.avito;

import com.DAO.services.HolderService;
import com.cars_annot.Holder;
import com.cars_annot.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private HolderService holderService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Holder holder = holderService.findByLogin(s);
        List<GrantedAuthority> authorities = buildUserAuthority(holder.getRoles());
        return buildUserForAuthentication(holder, authorities);
    }

    private User buildUserForAuthentication(Holder holder,
                                            List<GrantedAuthority> authorities) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String encoded = "{noop}" + holder.getPassword();
        return new User(holder.getLogin(),
                encoded, true,
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> roles) {

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            Result.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return Result;
    }
}
