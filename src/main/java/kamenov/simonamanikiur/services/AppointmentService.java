package kamenov.simonamanikiur.services;

import kamenov.simonamanikiur.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);

    List<Appointment> findAllAppointments();
}
