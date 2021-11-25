import { Injectable } from '@angular/core';
import { JwtService } from './jwt.service';
import { MainService } from './main.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private apiService: MainService,
    private jwtService: JwtService
  ) {}

  attemptLogin(credentials) {
    return this.apiService.post('/login', credentials);
  }
  userSignUp(user) {
    return this.apiService.post('/api/v1/user/registration', user);
  }
}
