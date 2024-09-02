package com.example.car_managment.notification.repo;

import com.example.car_managment.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByEmployeeId(Long recipientId);
}
