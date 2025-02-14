package kamenov.simonamanikiur.web;

import jakarta.validation.Valid;
import kamenov.simonamanikiur.entity.Appointment;
import kamenov.simonamanikiur.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> bookAppointment(@Valid @RequestBody Appointment appointment,
                                             BindingResult result) {
        if(result.hasErrors()){
            String errorMessage = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Appointment savedAppointment = appointmentService.bookAppointment(appointment);
        return ResponseEntity.ok(savedAppointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAllAppointments();
    }
}
