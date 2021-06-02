package com.crud.springboot.thymeleaf.service;

import com.crud.springboot.thymeleaf.dao.EmployeeRepository;
import com.crud.springboot.thymeleaf.dao.RoleRepository;
import com.crud.springboot.thymeleaf.dao.UserRepository;
import com.crud.springboot.thymeleaf.entity.Employee;
import com.crud.springboot.thymeleaf.entity.Role;
import com.crud.springboot.thymeleaf.entity.User;
import com.crud.springboot.thymeleaf.user.NewSystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(NewSystemUser newSystemUser) {
        User user = new User();
        Employee employee = new Employee();

        user.setUserName(newSystemUser.getUserName());
        user.setPassword(passwordEncoder.encode(newSystemUser.getPassword()));

        employee.setFirstName(newSystemUser.getFirstName());
        employee.setLastName(newSystemUser.getLastName());
        employee.setEmail(newSystemUser.getEmail());

        user.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));

        userRepository.save(user);
        employeeRepository.save(employee);
    }

    @Override
    public void savePermissions(User user) {

        Collection<Role> roles = user.getRoles();

        if (roles == null || roles.isEmpty()){
            user.setRoles(singletonList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
        }
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }


    @Override
    public User findById(int theId) {

        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theUser;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}




