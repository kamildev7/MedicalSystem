package com.medicalsystem.controller;

import com.medicalsystem.model.Patient;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {

    private final PatientService patientService;

    /**
     * Checks if entity exists.
     * @param  id id of the entity
     * @return    true if exists, false otherwise
     */
    @GetMapping("api/patients/{id}")
    public boolean patientExists(@PathVariable int id) {
        return patientService.exists(id);
    }

    /**
     * Creates an empty Patient entity, setting only its id.
     * @param  id id of the entity
     * @return    true if success, false otherwise
     */
    @PostMapping("api/patients/{id}")
    public boolean createPatient(@PathVariable int id) {

        /* Check if there's a patient with the given id */
        if (patientService.exists(id))
            return false;

        /* Create new patient with given id */
        Patient patient = new Patient();
        patient.setId(id);

        /* Persist patient */
        patient = patientService.saveOrUpdate(patient);

        if (patient == null)
            return false;

        /* Return response */
        return true;
    }

}
