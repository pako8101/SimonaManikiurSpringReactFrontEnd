package kamenov.simonamanikiur.services;

import kamenov.simonamanikiur.entity.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentService {
    List<Treatment> getAllTreatments();

    Optional<Treatment> getTreatmentById(Long id);

    Treatment createTreatment(Treatment treatment);

    Treatment updateTreatment(Long id, Treatment updatedTreatment);

    void deleteTreatment(Long id);

    List<Treatment> findByCategory(String manicure);
}
