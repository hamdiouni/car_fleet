package com.example.car_managment.cars.repo;

import com.example.car_managment.cars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
  Optional<Car> findByLicensePlate(String licensePlate);
}
