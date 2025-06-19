package com.luv2code.todos.service;

import com.luv2code.todos.entity.Authorities;
import com.luv2code.todos.entity.User;
import com.luv2code.todos.repository.UserRepository;
import com.luv2code.todos.response.UserResponse;
import com.luv2code.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final UserRepository userRepository;

    public UserServiceImpl(FindAuthenticatedUser findAuthenticatedUser, UserRepository userRepository) {
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(
                user.getId(),
                user.getAuthorities().stream().map(auth -> (Authorities) auth).toList(),
                user.getEmail(),
                user.getFirstName()+" "+user.getLastName()
        );
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();
        if(isLastAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Admin can't delete itself");
        }
        userRepository.delete(user);
    }

    private boolean isLastAdmin(User user){
        boolean isAdmin = user.getAuthorities().stream().anyMatch(auth->"ROLE_ADMIN".equals(auth.getAuthority()));
        if(isAdmin){
            long adminCount = userRepository.countAdminUser();

            return  adminCount <= 1;
        }
        return  false;
    }


}





