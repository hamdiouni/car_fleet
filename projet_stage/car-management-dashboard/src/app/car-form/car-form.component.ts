import { Component, OnInit } from '@angular/core';
import { CarService } from '../services/car.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  carForm: FormGroup;

  constructor(
    private carService: CarService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.carForm = this.fb.group({
      id: [null],
      model: ['', Validators.required],
      licensePlate: ['', Validators.required],
      status: ['', Validators.required],
      leasingContract: [null],
      insurance: [null],
      technicalVisit: [null],
      technicalVisitDate: [null, Validators.required],
      employeeIdentityCard: ['', Validators.required]  // Added field
    });

  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.carService.getCar(+id).subscribe(data => {
        this.carForm.patchValue(data);
      });
    }
  }

  onFileChange(event: any, fieldName: string): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64String = (reader.result as string).split(',')[1];
        this.carForm.patchValue({
          [fieldName]: base64String
        });
      };
      reader.readAsDataURL(file);
    }
  }

  saveCar(): void {
    if (this.carForm.valid) {
      if (this.carForm.value.id) {
        this.carService.updateCar(this.carForm.value.id, this.carForm.value).subscribe(() => {
          this.router.navigate(['/cars']);
          window.location.reload();
        });
      } else {
        this.carService.createCar(this.carForm.value).subscribe(() => {
          this.router.navigate(['/cars']);
        });
      }
    }
  }
}
