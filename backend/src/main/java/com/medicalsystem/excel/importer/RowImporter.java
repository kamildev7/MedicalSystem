package com.medicalsystem.excel.importer;

import com.medicalsystem.excel.builder.AdmissionBuilder;
import com.medicalsystem.excel.builder.OperationBuilder;
import com.medicalsystem.excel.builder.PatientBuilder;
import com.medicalsystem.model.*;
import com.medicalsystem.service.AdmissionService;
import com.medicalsystem.service.OperationService;
import com.medicalsystem.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class RowImporter {

    private final PatientBuilder patientBuilder;
    private final AdmissionBuilder admissionBuilder;
    private final OperationBuilder operationBuilder;

    private final PatientService patientService;
    private final OperationService operationService;
    private final AdmissionService admissionService;

    /**
     * Builds entity dependencies based on a single spreadsheed row.
     * Explicitly creates only three main objects (Patient, Operation, Admission) - the remaining objects are created in a cascade way.
     *
     * @param row spreadsheet row
     */
    public void importToDB(Row row) {

        /* Patient */
        Patient patient = patientBuilder.build(row);
        //TODO: czy istnieje
        patientService.saveOrUpdate(patient);

        /* Operation */
        Operation operation = operationBuilder.build(row);
        operationService.saveOrUpdate(operation);

        /* Admission */
        Admission admission = admissionBuilder.build(row, patient, operation);
        admissionService.saveOrUpdate(admission);

        log.info("ROW PERSISTED: " + row.getRowNum());
    }



}
