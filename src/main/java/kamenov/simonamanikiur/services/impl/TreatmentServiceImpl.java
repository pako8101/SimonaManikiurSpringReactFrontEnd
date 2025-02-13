package kamenov.simonamanikiur.services.impl;

import kamenov.simonamanikiur.entity.Treatment;
import kamenov.simonamanikiur.repos.TreatmentRepository;
import kamenov.simonamanikiur.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {
    @Autowired
    private TreatmentRepository treatmentRepository;
@Override
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }
    @Override
    public Optional<Treatment> getTreatmentById(Long id) {
        return treatmentRepository.findById(id);
    }
    @Override
    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }
    @Override
    public Treatment updateTreatment(Long id, Treatment updatedTreatment) {
        return treatmentRepository.findById(id).map(treatment -> {
            treatment.setName(updatedTreatment.getName());
            treatment.setDescription(updatedTreatment.getDescription());
            treatment.setPrice(updatedTreatment.getPrice());
            treatment.setCategory(updatedTreatment.getCategory());
            treatment.setImageUrl(updatedTreatment.getImageUrl());
            return treatmentRepository.save(treatment);
        }).orElseGet(() -> {
            updatedTreatment.setId(id);
            return treatmentRepository.save(updatedTreatment);
        });
    }
    @Override
    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }
}
