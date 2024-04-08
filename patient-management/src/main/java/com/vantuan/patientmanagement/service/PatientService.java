package com.vantuan.patientmanagement.service;

import com.vantuan.common.mapper.MappingUtil;
import com.vantuan.crud.service.BaseService;
import com.vantuan.patientmanagement.clinician.model.entity.Clinician;
import com.vantuan.patientmanagement.clinician.repository.ClinicianDAO;
import com.vantuan.patientmanagement.common.address.model.entity.UserAddress;
import com.vantuan.patientmanagement.criteria.PatientCriteria;
import com.vantuan.patientmanagement.exceptions.ResourceNotCreatedException;
import com.vantuan.patientmanagement.exceptions.ResourceNotUpdatedException;
import com.vantuan.patientmanagement.model.data.PatientData;
import com.vantuan.patientmanagement.model.entity.Patient;
import com.vantuan.patientmanagement.repository.PatientDAO;
import com.vantuan.patientmanagement.security.AuthenticatedUser;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService extends BaseService<Patient, Long, PatientCriteria> {

    private final PatientDAO patientDAO;
    private final MappingUtil mappingUtil;
    private final ClinicianDAO clinicianDAO;

    @Transactional
//    public Patient create(final PatientData.Create data, final AuthenticatedUser currentUser) {
//        log.info("Creating patient with userUuid : {}", currentUser.getUserID());
//        Clinician clinician = clinicianDAO.getClinicianId(currentUser.getUserID());
    public Patient create(final PatientData.Create data) {
        log.info("Creating patient with userUuid : {}");
        Clinician clinician = clinicianDAO.getClinicianId(1L);
        data.setClinician(clinician);
        Patient patient = Try.of(() -> super.save(mappingUtil.map(data, Patient.class)))
                .getOrElseThrow(ResourceNotCreatedException::new);

        return patient;
    }

    @Transactional
    public Patient edit(final PatientData.Edit data, final AuthenticatedUser currentUser) {
        final Patient patient = patientDAO.getByIdOrThrow(currentUser.getUserID());
        log.info("Editing patient with user id : {}", currentUser.getUserID());
        return Try.of(() -> super.update(updateData(patient, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }

    @Transactional
    public Patient edit(final PatientData.Edit data, final Long id) {
        final Patient patient = patientDAO.getByIdOrThrow(id);
        log.info("Editing patient with id : {}", id);
        return Try.of(() -> super.update(updateData(patient, data)))
                .getOrElseThrow(ResourceNotUpdatedException::new);
    }
//
//    @Transactional
//    public Patient getLoggedPatient(final String userUuid) {
//        log.info("Retrieving patient with userUuid : {}", userUuid);
//        return patientDAO.getByUserUuidOrThrow(userUuid);
//    }
//
//    @Transactional
//    public Patient getPatientByIdAndClinicianUuid(final Long patientId, final String clinicianUuid) {
//        log.info("Retrieving patient with id : {}", patientId);
//        return patientDAO.getByPatientIdAndClinicianUuidOrThrow(patientId, clinicianUuid);
//    }
//
//    @Transactional
//    public Patient deactivate(final Patient patient) {
//        log.info("Setting patient with userUuid : {} as deactivated", patient.getUserUuid());
//        Patient patientSave = Try.of(() -> super.update(patient.toBuilder()
//                        .active(false)
//                        .build()))
//                .getOrElseThrow(ResourceNotUpdatedException::new);
//        notificationService.notifyClinician(patient.getClinician(),
//                NotificationMessage.builder()
//                        .title("Your patient has been deactivated")
//                        .body("Patient " + patient.getFullName() + " deactivated")
//                        .notificationType(NotificationType.PATIENT_DEACTIVATION_REMOVAL)
//                        .build());
//        return patientSave;
//    }
//
//    @Transactional
//    public void deleteById(final Long id) {
//        final Patient patient = patientDAO.getByIdOrThrow(id);
//        super.delete(patient);
//        notificationService.notifyClinician(patient.getClinician(),
//                NotificationMessage.builder()
//                        .title("Your patient has been deleted")
//                        .body("Patient " + patient.getFullName() + " deleted")
//                        .notificationType(NotificationType.PATIENT_DEACTIVATION_REMOVAL)
//                        .build());
//    }
//
//    public void deleteAllByIds(Set<Long> ids) {
//        ids.forEach(id -> {
//            final Patient patient = patientDAO.getByIdOrThrow(id);
//            super.delete(patient);
//            notificationService.notifyClinician(patient.getClinician(),
//                    NotificationMessage.builder()
//                            .title("Your patient has been deleted")
//                            .body("Patient " + patient.getFullName() + " deleted")
//                            .notificationType(NotificationType.PATIENT_DEACTIVATION_REMOVAL)
//                            .build());
//        });
//    }

//    @Transactional
//    public Patient changeClinician(final Long patientId, final Long clinicianId) {
//        log.info("Changing patient with id : {} clinician", patientId);
//        Patient patient = patientDAO.getByIdOrThrow(patientId);
//        Clinician clinician = clinicianDAO.getByIdOrThrow(clinicianId);
//        Patient patientWithUpdatedClinician = Try.of(() -> super.update(patient.toBuilder()
//                        .clinician(clinician)
//                        .build()))
//                .getOrElseThrow(ResourceNotUpdatedException::new);
//        notificationService.notifyPatient(patient,
//                NotificationMessage.builder()
//                        .title("You have new clinician assigned")
//                        .body("New Clinician " + clinician.getFullName() + " assigned")
//                        .notificationType(NotificationType.REGISTRATION)
//                        .build());
//        return patientWithUpdatedClinician;
//    }


//    @Transactional(readOnly = true)
//    public boolean isPatientOfClinician(final Long patientId, final Long clinicianId){
//        return patientDAO.query().select(patient.id)
//                .from(patient)
//                .where(patient.id.eq(patientId).and(patient.clinician.id.eq(clinicianId))).fetchOne() != null;
//    }

    private Patient updateData(final Patient patient, final PatientData.Edit data) {
        return patient.toBuilder()
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .gender(data.getGender())
                .height(data.getHeight())
                .weight(data.getWeight())
                .userAddress(mappingUtil.map(data.getUserAddress(), UserAddress.class))
                .build();
    }
}

