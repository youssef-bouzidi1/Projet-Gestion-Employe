import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';
import { User } from './models/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'HRMS System';
  @ViewChild('sidenav') sidenav!: MatSidenav;
  currentUser: Observable<User | null>; // Change currentUser$ to currentUser

  constructor(private authService: AuthService) {
    this.currentUser = this.authService.currentUser; // Change currentUser$ to currentUser
  }

  toggleSidenav() {
    this.sidenav.toggle();
  }

  logout() {
    this.authService.logout();
  }
}