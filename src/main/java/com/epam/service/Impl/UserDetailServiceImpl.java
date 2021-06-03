package com.epam.service.Impl;

import com.epam.dao.ClientDao;
import com.epam.model.Client;
import com.epam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private ClientDao clientDao;

    @Autowired
    public UserDetailServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDao.getByLogin(username);
        if (client == null) {
            throw new UsernameNotFoundException("This user is not admin!");
        }
        Role role = clientDao.getByLogin(username).getRole();
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (role != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            grantList.add(authority);
        }
        return new User(client.getLogin(), client.getPassword(), grantList);
    }

}
