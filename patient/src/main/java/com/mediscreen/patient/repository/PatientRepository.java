package com.mediscreen.patient.repository;

import com.mediscreen.patient.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    Optional<Patient> findByGivenNameAndFamilyName(String givenName, String familyName);
}
