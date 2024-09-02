import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MaintenanceService {
    private baseUrl = 'http://localhost:8080/api/maintenances';

    constructor(private http: HttpClient) { }

    getMaintenances(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
    }

    createMaintenance(formData: FormData): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, formData);
    }

    updateMaintenance(id: number, formData: FormData): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, formData);
    }

    deleteMaintenance(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }

    getMaintenance(id: number): Observable<Object> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    downloadInvoiceFile(id: number): Observable<Blob> {
        return this.http.get(`${this.baseUrl}/${id}/file`, { responseType: 'blob' });
    }

    getTotalCost(): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}/total-cost`);
    }

    getCostByLicensePlate(licensePlate: string): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}/cost/${licensePlate}`);
    }
}
