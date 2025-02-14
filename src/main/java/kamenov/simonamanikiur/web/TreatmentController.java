package kamenov.simonamanikiur.web;

import jakarta.validation.Valid;
import kamenov.simonamanikiur.entity.Treatment;
import kamenov.simonamanikiur.repos.TreatmentRepository;
import kamenov.simonamanikiur.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "http://localhost:3000")  // Позволява връзка с React приложението
public class TreatmentController {
private final TreatmentRepository treatmentRepository;
    @Autowired
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentRepository treatmentRepository, TreatmentService treatmentService) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public Optional<Treatment> getTreatmentById(@PathVariable Long id) {
        return treatmentService.getTreatmentById(id);
    }

    @PostMapping
    public ResponseEntity<?> createTreatment(@RequestBody Treatment treatment, BindingResult result) {
        if(result.hasErrors()){
            String errorMessage = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Treatment savedTreatment = treatmentRepository.save(treatment);
        return ResponseEntity.ok(savedTreatment);

    }

    @PutMapping("/{id}")
    public Treatment updateTreatment(@PathVariable Long id,@Valid @RequestBody Treatment treatment) {

        return treatmentService.updateTreatment(id, treatment);
    }

//    @DeleteMapping("/{id}")
//    public void deleteTreatment(@PathVariable Long id) {
//        treatmentService.deleteTreatment(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTreatment(@PathVariable Long id) {
        if(!treatmentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        treatmentService.deleteTreatment(id);
        return ResponseEntity.ok("Treatment with id " + id + " was deleted successfully.");
    }
        // Отделни крайни точки за маникюр и педикюр
        @GetMapping("/manicure")
        public List<Treatment> getManicureTreatments() {
            return treatmentService.findByCategory("manicure");
        }

        @GetMapping("/pedicure")
        public List<Treatment> getPedicureTreatments() {
            return treatmentService.findByCategory("pedicure");
        }
    }



