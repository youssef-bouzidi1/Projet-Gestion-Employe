// src/app/services/auth.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../models/user.model';
import { catchError, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private currentUserSubject: BehaviorSubject<User | null>;
    public currentUser: Observable<User | null>;
    private apiUrl = 'http://localhost:8080/api/auth';

    constructor(
        private http: HttpClient,
        private router: Router
    ) {
        this.currentUserSubject = new BehaviorSubject<User | null>(
            JSON.parse(localStorage.getItem('currentUser') || 'null')
        );
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User | null {
        return this.currentUserSubject.value;
    }

    getUserId(): number | null {
        return this.currentUserValue?.id ?? null;
    }

    login(username: string, password: string): Observable<User | null> {
        return this.http.post<User>(`${this.apiUrl}/login`, { username, password })
            .pipe(
                map(user => {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                    return user;
                }),
                catchError(this.handleError)
            );
    }

    register(user: User): Observable<User> {
        return this.http.post<User>(`${this.apiUrl}/register`, user).pipe(
            catchError(this.handleError)
        );
    }

    logout() {
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
        this.router.navigate(['/login']);
    }

    isAuthenticated(): boolean {
        return !!this.currentUserValue;
    }

    isHR(): boolean {
        return this.currentUserValue?.role === 'HR';
    }

    isEmployee(): boolean {
        return this.currentUserValue?.role === 'EMPLOYEE';
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'An unknown error occurred!';
        if (error.error instanceof ErrorEvent) {
            errorMessage = `Error: ${error.error.message}`;
        } else {
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.error.message}`;
        }
        return throwError(() => new Error(errorMessage));
    }
}