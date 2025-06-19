package com.luv2code.todos.service;

import com.luv2code.todos.entity.Authorities;
import com.luv2code.todos.entity.User;
import com.luv2code.todos.repository.UserRepository;
import com.luv2code.todos.request.PasswordUpdateRequest;
import com.luv2code.todos.response.UserResponse;
import com.luv2code.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(FindAuthenticatedUser findAuthenticatedUser, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    @Transactional
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        if(!isOldPasswordCorrect(user.getPassword(),passwordUpdateRequest.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Current password is incorrect");
        }

        if(!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(),passwordUpdateRequest.getNewPassword2())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password do not match");
        }

        if(!isNewPasswordDifferent(passwordUpdateRequest.getOldPassword(),passwordUpdateRequest.getNewPassword2())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Old and New Password are Match");
        }
        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isNewPasswordDifferent(String newPassword , String oldPassword ){
        return !oldPassword.equals(newPassword);
    }


    private boolean isOldPasswordCorrect(String currentPassword , String oldPassword ){
        return passwordEncoder.matches(oldPassword,currentPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword , String newPasswordConfirmation){
       return newPassword.equals(newPasswordConfirmation);
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





