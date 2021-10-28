package org.launchcode.liftoffproject.auth;

import org.launchcode.liftoffproject.models.User;
import org.launchcode.liftoffproject.models.dto.LoginRegisterFormDTO;

public interface UserService {

    public User save(LoginRegisterFormDTO loginRegisterFormDTO) throws UserAlreadyExistsAuthenticationException;
    public User findByUsername(String username);

}
