package com.example.car_managment.maint.service;

import com.example.car_managment.cars.entities.Car;
import com.example.car_managment.cars.repo.CarRepository;
import com.example.car_managment.email.EmailService;
import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.employees.repo.EmployeeRepository;
import com.example.car_managment.maint.dto.MaintenanceDTO;
import com.example.car_managment.maint.entities.Maintenance;
import com.example.car_managment.maint.repo.MaintenanceRepository;
import com.example.car_managment.notification.service.NotificationService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceService {

  @Autowired
  private MaintenanceRepository maintenanceRepository;

  @Autowired
  private CarRepository carRepository;
@Autowired
private EmployeeRepository userRepository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private NotificationService notificationService;
  public List<MaintenanceDTO> findAll() {
    return maintenanceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public MaintenanceDTO save(Maintenance maintenance) {
    if (maintenance.getInvoiceDate() == null) {
      maintenance.setInvoiceDate(new Date());
    }
    Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
    // Notify the admin, car owner, and logistics employee
    Employee admin = userRepository.findByRole(Employee.UserRole.ADMIN).get(0); // Assuming one admin
    Employee carOwner = maintenance.getCar().getEmployee();
    Employee logisticsEmployee = userRepository.findByRole(Employee.UserRole.LOGISTICS).get(0);

    String message = "Maintenance scheduled for car: " + maintenance.getCar().getLicensePlate();
    notificationService.sendNotification(message, admin.getId());
    notificationService.sendNotification(message, carOwner.getId());
    notificationService.sendNotification(message, logisticsEmployee.getId());
    // Envoyer l'e-mail à l'administrateur avec les détails de la maintenance
    String adminEmail = "hamdi.ouni@esprit.tn";
    String subject = "Nouvelle Maintenance Ajoutée";

    // Construire l'URL pour le détail de la maintenance
    String maintenanceDetailUrl = "http://localhost:4200/maintenance-detail/" + savedMaintenance.getId();

    // Créer le contenu de l'email en HTML
    StringBuilder text = new StringBuilder();
    text.append("<html><body>")
      .append("<p>Une nouvelle maintenance a été ajoutée pour la voiture avec la plaque : ")
      .append(maintenance.getCar().getLicensePlate()).append("</p>")
      .append("<p>Date Planifiée : ").append(maintenance.getScheduledDate()).append("</p>")
      .append("<p>Statut : ").append(maintenance.getStatus()).append("</p>")
      .append("<p>Coût : ").append(maintenance.getCout()).append("</p>")
      .append("<p>Facture (PDF) : Vous pouvez télécharger la facture via le système.</p>")
      .append("<p>Détails de la Maintenance : Cliquez sur le <a href=\"").append(maintenanceDetailUrl)
      .append("\">lien</a> pour voir les détails.</p>")
      .append("</body></html>");

    emailService.sendEmail(adminEmail, subject, text.toString(), true); // Pass `true` to indicate HTML content

    return convertToDTO(savedMaintenance);
  }



  public MaintenanceDTO findById(Long id) {
    Maintenance maintenance = maintenanceRepository.findById(id).orElse(null);
    return convertToDTO(maintenance);
  }

  public void deleteById(Long id) {
    maintenanceRepository.deleteById(id);
  }

  public byte[] saveFile(MultipartFile file) {
    try {
      return file.getBytes();
    } catch (IOException e) {
      throw new RuntimeException("Erreur lors de l'enregistrement du fichier", e);
    }
  }

  public byte[] getFile(Long id) {
    Maintenance maintenance = maintenanceRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
    return maintenance.getInvoiceFile();
  }

  public byte[] convertImageToPdf(byte[] imageData) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      PdfWriter writer = new PdfWriter(baos);
      PdfDocument pdfDoc = new PdfDocument(writer);
      Document document = new Document(pdfDoc);

      ImageData imageDataFactory = ImageDataFactory.create(imageData);
      Image img = new Image(imageDataFactory);
      //document.add(new Paragraph("Maintenance Invoice"));
      document.add(img);

      document.close();
      return baos.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException("Erreur lors de la conversion de l'image en PDF", e);
    }
  }

  public MaintenanceDTO createMaintenanceWithCarPlate(Maintenance maintenance, MultipartFile file, String licensePlate) {
    Car car = carRepository.findByLicensePlate(licensePlate)
      .orElseThrow(() -> new RuntimeException("Voiture non trouvée avec la plaque d'immatriculation : " + licensePlate));

    maintenance.setCar(car);
    byte[] fileContent = saveFile(file);

    // Convertir l'image en PDF
    byte[] pdfContent = convertImageToPdf(fileContent);
    maintenance.setInvoiceFile(pdfContent);

    return save(maintenance);
  }

  private MaintenanceDTO convertToDTO(Maintenance maintenance) {
    if (maintenance == null) {
      return null;
    }
    MaintenanceDTO dto = new MaintenanceDTO();
    dto.setId(maintenance.getId());
    dto.setScheduledDate(String.valueOf(maintenance.getScheduledDate()));
    dto.setStatus(maintenance.getStatus());
    dto.setNotes(maintenance.getNotes());
    dto.setCout((float) maintenance.getCout());

    dto.setCarId(maintenance.getCar() != null ? maintenance.getCar().getId() : null);
    dto.setLicensePlate(maintenance.getCar() != null ? maintenance.getCar().getLicensePlate() : null);

    dto.setInvoiceFile(maintenance.getInvoiceFile());
    return dto;
  }

  public double calculateTotalCost() {
    return maintenanceRepository.sumAllMaintenanceCosts();
  }

  public double calculateCostByLicensePlate(String licensePlate) {
    return maintenanceRepository.sumMaintenanceCostByLicensePlate(licensePlate);
  }
}
