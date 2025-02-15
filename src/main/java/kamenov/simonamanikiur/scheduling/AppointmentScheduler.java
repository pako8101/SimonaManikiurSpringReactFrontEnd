package kamenov.simonamanikiur.scheduling;

import kamenov.simonamanikiur.entity.Appointment;
import kamenov.simonamanikiur.repos.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class AppointmentScheduler {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${email_username}") private String sender;
    private final AppointmentRepository appointmentRepository;

    public AppointmentScheduler(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Задайте интервал за проверка – всяка минута (60000 милисекунди)
    @Scheduled(fixedDelay = 60000)
    public void checkAndProcessAppointments() {
        // Извличаме всички appointment-и, чиито дата/час е в миналото
        List<Appointment> expiredAppointments = appointmentRepository.findAll().stream()
                .filter(app -> app.getAppointmentDate().isBefore(LocalDateTime.now()))
                .toList();

        for (Appointment appointment : expiredAppointments) {
            // Изпращаме имейл нотификация
            sendEmailNotification(appointment);
            // Изтриваме appointment-а от базата
            appointmentRepository.delete(appointment);
        }
    }
    private void sendEmailNotification(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sender); // Заменете с реалния администраторски имейл
        message.setSubject("Appointment Expired: " + appointment.getCustomerName());
        message.setText("Appointment scheduled for " + appointment.getAppointmentDate() +
                " has expired and been removed from the system.");
        mailSender.send(message);
    }
}
