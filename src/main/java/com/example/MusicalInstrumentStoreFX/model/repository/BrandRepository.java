package com.example.MusicalInstrumentStoreFX.model.repository;

import com.example.MusicalInstrumentStoreFX.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
