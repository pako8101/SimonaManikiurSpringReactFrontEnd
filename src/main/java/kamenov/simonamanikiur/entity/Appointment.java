package kamenov.simonamanikiur.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity{


    @NotBlank(message = "Моля, въведете име")
    private String customerName;

    @NotBlank(message = "Моля, въведете телефон")
    private String customerPhone;

    @NotNull(message = "Моля, изберете дата и час")
    private LocalDateTime appointmentDate;

    @NotBlank(message = "Моля, изберете вид услуга")
    private String treatmentType; // "manicure" или "pedicure"

    private String notes;

    public Appointment() {
    }

    public @NotBlank(message = "Моля, въведете име") String getCustomerName() {
        return customerName;
    }

    public Appointment setCustomerName(@NotBlank(message = "Моля, въведете име") String customerName) {
        this.customerName = customerName;
        return this;
    }

    public @NotBlank(message = "Моля, въведете телефон") String getCustomerPhone() {
        return customerPhone;
    }

    public Appointment setCustomerPhone(@NotBlank(message = "Моля, въведете телефон") String customerPhone) {
        this.customerPhone = customerPhone;
        return this;
    }

    public @NotNull(message = "Моля, изберете дата и час") LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public Appointment setAppointmentDate(@NotNull(message = "Моля, изберете дата и час") LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    public @NotBlank(message = "Моля, изберете вид услуга") String getTreatmentType() {
        return treatmentType;
    }

    public Appointment setTreatmentType(@NotBlank(message = "Моля, изберете вид услуга") String treatmentType) {
        this.treatmentType = treatmentType;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Appointment setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}

