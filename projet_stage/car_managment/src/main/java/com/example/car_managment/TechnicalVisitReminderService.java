package com.example.car_managment;

import com.example.car_managment.cars.entities.Car;
import com.example.car_managment.cars.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TechnicalVisitReminderService {

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private SmsService smsService;

  @Scheduled(cron = "0 00 08 * * ?") // Exécution tous les jours à 8h du matin
  public void checkTechnicalVisits() {
    List<Car> cars = carRepository.findAll();
    LocalDate today = LocalDate.now();

    for (Car car : cars) {
      if (car.getTechnicalVisitDate() != null) {
        long daysBetween = ChronoUnit.DAYS.between(today, car.getTechnicalVisitDate());
        if (daysBetween == 30) {
          sendReminder(car);
        }
      }
    }
  }

  private void sendReminder(Car car) {
    String recipientEmail = car.getEmployee().getEmail(); // Adresse e-mail de l'employé
    String recipientPhoneNumber = String.valueOf(car.getEmployee().getPhoneNumber()); // Numéro de téléphone de l'employé

    // Envoyer l'e-mail à l'employé
    SimpleMailMessage employeeMessage = new SimpleMailMessage();
    employeeMessage.setTo(recipientEmail);
    employeeMessage.setSubject("Rappel : Visite technique pour la voiture " + car.getLicensePlate());
    employeeMessage.setText("La visite technique de la voiture avec la plaque d'immatriculation " +
      car.getLicensePlate() + " est prévue pour le " + car.getTechnicalVisitDate() + ". Veuillez la préparer.");
    mailSender.send(employeeMessage);

    // Envoyer le SMS à l'employé
    String smsContent = "Rappel : La visite technique de la voiture " +
      car.getLicensePlate() + " est prévue pour le " + car.getTechnicalVisitDate();
    smsService.sendSms(recipientPhoneNumber, smsContent);

    // Envoyer l'e-mail à l'administrateur
    SimpleMailMessage adminMessage = new SimpleMailMessage();
    adminMessage.setTo("hamdi.ouni@esprit.tn"); // Adresse e-mail de l'administrateur
    adminMessage.setFrom("ounihamdi4@gmail.com"); // Votre adresse e-mail
    adminMessage.setSubject("Rappel envoyé : Visite technique pour la voiture " + car.getLicensePlate());
    adminMessage.setText("Un rappel a été envoyé pour la visite technique de la voiture avec la plaque d'immatriculation " +
      car.getLicensePlate() + " qui est prévue pour le " + car.getTechnicalVisitDate() + ".");
    mailSender.send(adminMessage);
  }

}

