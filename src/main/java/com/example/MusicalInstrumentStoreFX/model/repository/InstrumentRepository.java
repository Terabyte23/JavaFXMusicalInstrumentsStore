package com.example.MusicalInstrumentStoreFX.model.repository;

import com.example.MusicalInstrumentStoreFX.model.entity.Instruments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository  extends JpaRepository<Instruments, Long> {
}
