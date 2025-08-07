package afriqueMed.business;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Speciality;
import afriqueMed.domain.users.Client;
import afriqueMed.domain.users.Technician;
import afriqueMed.domain.users.User;
import afriqueMed.infra.usersRepos.ClientRepository;
import afriqueMed.infra.usersRepos.ManagerRepository;
import afriqueMed.infra.usersRepos.TechnicianRepository;
import afriqueMed.infra.usersRepos.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService{

    @Inject
    UserRepository userRepository;
    @Inject
    TechnicianRepository technicianRepository;
    @Inject
    ManagerRepository managerRepository;
    @Inject
    ClientRepository clientRepository;

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }public List<Technician> getAllTechnician() {
        return technicianRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
    @Transactional
    public User getOrCreateUserFromKeycloak(String keycloakId, String name, String email, String phone) {
        // 1. Check if user already exists
        User existing = userRepository.findByKeycloakId(keycloakId);
        if (existing != null) return existing;

        // 2. Create new user (e.g. Client — you can customize this logic per role)
        Client newUser = new Client();
        newUser.setKeycloakId(keycloakId);
        newUser.setName(name);
        newUser.setPhone(phone);
        newUser.setAddress("N/A"); // You can update this later
        newUser.setCIN("N/A");     // Optional

        userRepository.save(newUser);
        return newUser;
    }
    public Client createClient(String keycloakId, String CIN, String name, String address, String phone) {
        Client client = new Client();
        client.setKeycloakId(keycloakId);
        client.setCIN(CIN);
        client.setName(name);
        client.setAddress(address);
        client.setPhone(phone);

        clientRepository.save(client);
        return client;
    }

    @Transactional
    public User updateUser(Long id, String keycloakId, String CIN, String name, String address, String phone) {
        User user = userRepository.findById(id);
        if (user == null) {
            return null;
        }

        if (keycloakId != null) user.setKeycloakId(keycloakId);
        if (CIN != null) user.setCIN(CIN);
        if (name != null) user.setName(name);
        if (address != null) user.setAddress(address);
        if (phone != null) user.setPhone(phone);

        userRepository.save(user);
        return user;
    }
    @Transactional
    public Technician createTechnician(String keycloakId, String name, String phone, String cin, List<Speciality> specialties, CountryEnum location) {
        User existing = userRepository.findByKeycloakId(keycloakId);
        if (existing instanceof Technician technician) {
            return technician;
        }

        Technician technician = new Technician();
        technician.setKeycloakId(keycloakId);
        technician.setName(name);
        technician.setPhone(phone);
        technician.setCIN(cin);
        technician.setSpecialities(specialties);
        technician.setLocation(location);
        technicianRepository.save(technician);
        return technician;
    }
    public User findByKeycloakId(String keycloakId) {
        return userRepository.findByKeycloakId(keycloakId);
    }
    public List<Technician> getAvailableTechnicians() {
        return technicianRepository.findAvailableTechnicians();
    }
    @Transactional
    public Technician setTechnicianVacationStatus(Long technicianId, boolean onVacation) {
        Technician technician = technicianRepository.findById(technicianId);
        if (technician == null) {
            return null;
        }
        technician.setOnVacation(onVacation);
        return technician;
    }


}
