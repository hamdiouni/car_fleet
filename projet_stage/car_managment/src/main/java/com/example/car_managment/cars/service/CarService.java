package com.example.car_managment.cars.service;

import com.example.car_managment.cars.dto.CarDTO;
import com.example.car_managment.cars.entities.Car;
import com.example.car_managment.cars.repo.CarRepository;
import com.example.car_managment.employees.repo.EmployeeRepository;
import com.example.car_managment.maint.entities.Maintenance;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
  @Autowired
  private CarRepository carRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  public List<CarDTO> findAll() {
    return carRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public CarDTO save(CarDTO carDTO) {
    Car car = new Car();
    car.setLicensePlate(carDTO.getLicensePlate());
    car.setModel(carDTO.getModel());
    car.setStatus(carDTO.getStatus());
    car.setLeasingContract(carDTO.getLeasingContract());
    car.setInsurance(carDTO.getInsurance());
    car.setTechnicalVisit(carDTO.getTechnicalVisit());
    car.setTechnicalVisitDate(carDTO.getTechnicalVisitDate());

    // Link car with employee by identity card number
    employeeRepository.findByIdentityCardNumber(carDTO.getEmployeeIdentityCard()).ifPresent(car::setEmployee);

    Car savedCar = carRepository.save(car);
    return convertToDTO(savedCar);
  }

  public CarDTO findById(Long id) {
    Car car = carRepository.findById(id).orElse(null);
    return convertToDTO(car);
  }

  public void deleteById(Long id) {
    carRepository.deleteById(id);
  }

  public byte[] getFile1(Long id) {
    Car maintenance = carRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
    return maintenance.getInsurance();
  }
  public byte[] getFile2(Long id) {
    Car maintenance = carRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
    return maintenance.getTechnicalVisit();
  }
  public byte[] getFile3(Long id) {
    Car maintenance = carRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
    return maintenance.getLeasingContract();
  }
  /*public byte[] convertImageToPdf(byte[] imageData) {
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
  }*/
  private CarDTO convertToDTO(Car car) {
    if (car == null) {
      return null;
    }
    CarDTO dto = new CarDTO();
    dto.setId(car.getId());
    dto.setLicensePlate(car.getLicensePlate());
    dto.setModel(car.getModel());
    dto.setStatus(car.getStatus());
    dto.setEmployeeIdentityCard(car.getEmployee() != null ? car.getEmployee().getIdentityCardNumber() : null);
    dto.setLeasingContract(car.getLeasingContract());
    dto.setInsurance(car.getInsurance());
    dto.setTechnicalVisit(car.getTechnicalVisit());
    dto.setTechnicalVisitDate(car.getTechnicalVisitDate());
    return dto;
  }
}
