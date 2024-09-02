import { Component, OnInit } from '@angular/core';
import { CarService } from '../services/car.service';
import { Car } from '../car';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars: Car[] = [];
  carIdToDelete: number | null = null;

  constructor(private carService: CarService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadCars();
  }

  loadCars(): void {
    this.carService.getCars().subscribe(data => {
      this.cars = data;
    });
  }

  openDeleteConfirmationModal(id: number): void {
    this.carIdToDelete = id;
    const modalElement = document.getElementById('deleteConfirmationModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  confirmDelete(): void {
    if (this.carIdToDelete !== null) {
      this.carService.deleteCar(this.carIdToDelete).subscribe(() => {
        this.cars = this.cars.filter(car => car.id !== this.carIdToDelete);
        this.carIdToDelete = null;
        const modalElement = document.getElementById('deleteConfirmationModal');
        if (modalElement) {
          const modal = bootstrap.Modal.getInstance(modalElement);
          if (modal) {
            modal.hide();
          }
        }
      });
    }
  }

  confirmModify(): void {
    const confirmResult = window.confirm('Êtes-vous sûr de vouloir modifier cette voiture ?');
    if (!confirmResult) {
      return; // If the user cancels, prevent the action.
    }
    // Otherwise, the link will proceed with the navigation.
  }

  downloadFile1(id: number): void {
    this.carService.downloadInvoiceFile1(id).subscribe(file => {
      const url = window.URL.createObjectURL(file);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Contrat_de_Leasing.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  downloadFile2(id: number): void {
    this.carService.downloadInvoiceFile2(id).subscribe(file => {
      const url = window.URL.createObjectURL(file);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Assurance.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  downloadFile3(id: number): void {
    this.carService.downloadInvoiceFile3(id).subscribe(file => {
      const url = window.URL.createObjectURL(file);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Visite_Technique.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }
}
