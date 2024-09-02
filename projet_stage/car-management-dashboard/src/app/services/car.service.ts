import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../car';

@Injectable({
    providedIn: 'root'
})
export class CarService {
    [x: string]: any;
    private apiUrl = 'http://localhost:8080/api/cars';

    constructor(private http: HttpClient) { }

    getCars(): Observable<Car[]> {
        return this.http.get<Car[]>(this.apiUrl);
    }

    getCar(id: number): Observable<Car> {
        return this.http.get<Car>(`${this.apiUrl}/${id}`);
    }

    createCar(car: Car): Observable<Car> {
        return this.http.post<Car>(this.apiUrl, car, {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        });
    }

    updateCar(id: number, car: Car): Observable<Car> {
        return this.http.put<Car>(`${this.apiUrl}/${id}`, car, {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        });
    }

    deleteCar(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
    downloadInvoiceFile1(id: number): Observable<Blob> {
        return this.http.get(`${this.apiUrl}/${id}/file1`, { responseType: 'blob' });
    }
    downloadInvoiceFile2(id: number): Observable<Blob> {
        return this.http.get(`${this.apiUrl}/${id}/file2`, { responseType: 'blob' });
    }
    downloadInvoiceFile3(id: number): Observable<Blob> {
        return this.http.get(`${this.apiUrl}/${id}/file3`, { responseType: 'blob' });
    }
}
