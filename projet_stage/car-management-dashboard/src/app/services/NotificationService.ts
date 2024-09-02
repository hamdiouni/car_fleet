import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { NotificationDTO } from '../NotificationDTO'; // Assurez-vous d'avoir le DTO approprié

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    private baseUrl = 'http://localhost:8080/api/notifications';
    private notificationSubject = new Subject<NotificationDTO>();

    constructor(private http: HttpClient) { }

    // Fetch notifications for a specific user
    getNotificationsForUser(userId: number): Observable<NotificationDTO[]> {
        return this.http.get<NotificationDTO[]>(`${this.baseUrl}/${userId}`);
    }

    // React to a problem (e.g., mark the problem as addressed)
    reactToProblem(notificationId: number): Observable<void> {
        return this.http.post<void>(`${this.baseUrl}/react`, null, {
            params: { notificationId: notificationId.toString() }
        });
    }

    // Declare a new problem
    declareProblem(employeeId: number, description: string): Observable<void> {
        return this.http.post<void>(`${this.baseUrl}/declare`, null, {
            params: { employeeId: employeeId.toString(), description }
        });
    }

    // Connect to a notification stream
    connectToNotificationStream(userId: number): void {
        const eventSource = new EventSource(`${this.baseUrl}/stream/${userId}`);
        eventSource.addEventListener('notification', (event: MessageEvent) => {
            const notification: NotificationDTO = JSON.parse(event.data);
            this.notificationSubject.next(notification);
        });
    }

    // Get observable for notification updates
    getNotificationUpdates(): Observable<NotificationDTO> {
        return this.notificationSubject.asObservable();
    }
}
