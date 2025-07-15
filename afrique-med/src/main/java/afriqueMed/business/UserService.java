package afriqueMed.business;

import afriqueMed.domain.users.User;
import afriqueMed.infra.usersRepos.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService{

    @Inject
    UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
