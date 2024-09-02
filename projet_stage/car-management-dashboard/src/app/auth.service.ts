// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(email: string, identityCardNumber: string, role: string): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, {
      email,
      identityCardNumber,
      role
    });
  }

  checkUserRole(email: string, identityCardNumber: string, role: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/check-role`, {
      params: { email, identityCardNumber, role }
    });
  }
}
