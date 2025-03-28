package com.example.MusicalInstrumentStoreFX.service;

import com.example.MusicalInstrumentStoreFX.model.entity.AppUser;
import com.example.MusicalInstrumentStoreFX.model.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    public static AppUser currentUser;
    public enum ROLES {USER, MANAGER, ADMINISTRATOR};
    private AppUserRepository repository;
    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
        initSuperUser();
    }

    private void initSuperUser() {
        if (repository.count() > 0) {
            return;
        }
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("12345");
        admin.setFirstname("Admin");
        admin.setLastname("SuperAdmin");
        admin.getRoles().add(ROLES.ADMINISTRATOR.toString());
        admin.getRoles().add(ROLES.MANAGER.toString());
        admin.getRoles().add(ROLES.USER.toString());
        repository.save(admin);
    }

    public void add(AppUser user) {
        repository.save(user);
    }
    public boolean authentication(String username, String password) {
        Optional<AppUser> optionalAppUser = repository.findByUsername(username);
        if(optionalAppUser.isEmpty()) {
            return false;
        }
        AppUser loginUser = optionalAppUser.get();
        if(!loginUser.getPassword().equals(password)) {
            return false;
        }
        currentUser = loginUser;
        return true;
    }
}
