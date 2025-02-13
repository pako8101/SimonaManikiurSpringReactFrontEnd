package kamenov.simonamanikiur.web;

import kamenov.simonamanikiur.entity.Treatment;
import kamenov.simonamanikiur.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "http://localhost:3000")  // Позволява връзка с React приложението
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public Optional<Treatment> getTreatmentById(@PathVariable Long id) {
        return treatmentService.getTreatmentById(id);
    }

    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentService.createTreatment(treatment);
    }

    @PutMapping("/{id}")
    public Treatment updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment);
    }

    @DeleteMapping("/{id}")
    public void deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
    }
}

