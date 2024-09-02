// src/app/services/problem.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Problem, ProblemStatus } from '../problem'; // Assurez-vous que le chemin est correct

@Injectable({
    providedIn: 'root'
})
export class ProblemService {
    private apiUrl = 'http://localhost:8080/api/problems'; // Mettez à jour avec votre URL d'API

    constructor(private http: HttpClient) { }

    getProblemsForUser(userId: number): Observable<Problem[]> {
        return this.http.get<Problem[]>(`${this.apiUrl}/user/${userId}`);
    }

    reactToProblem(problemId: number): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/react`, { problemId });
    }

    declareProblem(employeeId: number, description: string): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/declare`, { employeeId, description });
    }
}
