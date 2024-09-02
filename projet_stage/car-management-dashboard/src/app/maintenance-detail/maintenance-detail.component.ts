import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MaintenanceService } from '../services/maintenance.service';

@Component({
  selector: 'app-maintenance-detail',
  templateUrl: './maintenance-detail.component.html',
  styleUrls: ['./maintenance-detail.component.css']
})
export class MaintenanceDetailComponent implements OnInit {
  maintenance: any;
  licensePlateCost: number = 0;

  constructor(
    private route: ActivatedRoute,
    private maintenanceService: MaintenanceService
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.maintenanceService.getMaintenance(id).subscribe(data => {
      this.maintenance = data;
      this.getCostByLicensePlate(this.maintenance.licensePlate);
    });
  }

  getCostByLicensePlate(licensePlate: string) {
    this.maintenanceService.getCostByLicensePlate(licensePlate).subscribe(cost => {
      this.licensePlateCost = cost;
    });
  }

  downloadFile(id: number) {
    this.maintenanceService.downloadInvoiceFile(id).subscribe(file => {
      const url = window.URL.createObjectURL(file);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'facture.pdf';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }
}
