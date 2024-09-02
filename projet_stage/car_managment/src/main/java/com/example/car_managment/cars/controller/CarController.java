package com.example.car_managment.cars.controller;

import com.example.car_managment.cars.dto.CarDTO;
import com.example.car_managment.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping
  public List<CarDTO> getAllCars() {
    return carService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
    CarDTO car = carService.findById(id);
    return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public CarDTO createCar(@RequestBody CarDTO carDTO) {
    return carService.save(carDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
    CarDTO updatedCar = carService.save(carDTO);
    return updatedCar != null ? ResponseEntity.ok(updatedCar) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCar(@PathVariable Long id) {
    carService.deleteById(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/{id}/file1")
  public byte[] getMaintenanceFile1(@PathVariable Long id) {
    return carService.getFile1(id);
  }
  @GetMapping("/{id}/file2")
  public byte[] getMaintenanceFile2(@PathVariable Long id) {
    return carService.getFile2(id);
  }
  @GetMapping("/{id}/file3")
  public byte[] getMaintenanceFile3(@PathVariable Long id) {
    return carService.getFile3(id);
  }

}
