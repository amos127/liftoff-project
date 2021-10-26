package org.launchcode.liftoffproject.auth;

import org.launchcode.liftoffproject.data.UserRepository;
import org.launchcode.liftoffproject.models.User;
import org.launchcode.liftoffproject.models.dto.LoginRegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User save(LoginRegisterFormDTO loginRegisterFormDTO) throws EmailExistsException {

        User existingUser = userRepository.findByUsername(loginRegisterFormDTO.getUsername());
        if (existingUser != null)
            throw new EmailExistsException("The username " + loginRegisterFormDTO.getUsername()
                    + " already exists.");

        User newUser = new User(
                loginRegisterFormDTO.getUsername(),
                passwordEncoder.encode(loginRegisterFormDTO.getPassword()));
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
