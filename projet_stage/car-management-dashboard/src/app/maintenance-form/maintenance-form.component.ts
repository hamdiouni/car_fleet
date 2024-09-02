import { Component, OnInit } from '@angular/core';
import { MaintenanceService } from '../services/maintenance.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Maintenance } from '../maintenance'; // Import the interface

@Component({
  selector: 'app-maintenance-form',
  templateUrl: './maintenance-form.component.html',
  styleUrls: ['./maintenance-form.component.css']
})
export class MaintenanceFormComponent implements OnInit {
  id!: number;
  maintenance: Maintenance = {} as Maintenance; // Type your maintenance object
  file!: File;
  licensePlate!: string;

  constructor(
    private maintenanceService: MaintenanceService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.maintenanceService.getMaintenance(this.id).subscribe(data => {
        this.maintenance = data as Maintenance;
        this.licensePlate = this.maintenance.licensePlate;
      });
    }
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
  }

  saveMaintenance() {
    const formData = new FormData();
    formData.append('maintenance', new Blob([JSON.stringify(this.maintenance)], {
      type: 'application/json'
    }));
    formData.append('licensePlate', this.licensePlate);
    if (this.file) {
      formData.append('file', this.file);
    }

    if (this.id) {
      this.maintenanceService.updateMaintenance(this.id, formData).subscribe(data => {
        this.router.navigate(['/maintenances']);
      });
    } else {
      this.maintenanceService.createMaintenance(formData).subscribe(data => {
        this.router.navigate(['/maintenances']);
      });
    }
  }
}
