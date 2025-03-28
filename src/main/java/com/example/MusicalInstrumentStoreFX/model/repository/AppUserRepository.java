package com.example.MusicalInstrumentStoreFX.model.repository;

import com.example.MusicalInstrumentStoreFX.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
