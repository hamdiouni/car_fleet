import { Component, OnInit } from '@angular/core';
import { MaintenanceService } from '../services/maintenance.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-maintenance-list',
  templateUrl: './maintenance-list.component.html',
  styleUrls: ['./maintenance-list.component.css']
})
export class MaintenanceListComponent implements OnInit {
  maintenances!: any[];

  constructor(private maintenanceService: MaintenanceService, private router: Router) { }

  ngOnInit() {
    this.maintenanceService.getMaintenances().subscribe(data => {
      this.maintenances = data;
    });
  }

  editMaintenance(id: number) {
    if (window.confirm('Êtes-vous sûr de vouloir modifier cette maintenance ?')) {
      this.router.navigate(['/edit-maintenance', id]); // Navigate to edit page with ID
    }
  }

  deleteMaintenance(id: number) {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cette maintenance ?')) {
      this.maintenanceService.deleteMaintenance(id).subscribe(() => {
        this.maintenances = this.maintenances.filter(m => m.id !== id);
      });
    }
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

  viewMaintenanceDetail(id: number) {
    this.router.navigate(['/maintenance-detail', id]); // Navigate to detail page with ID
  }
}
