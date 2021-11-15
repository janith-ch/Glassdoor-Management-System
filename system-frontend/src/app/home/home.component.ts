import { JwtService } from './../service/jwt.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  showFiller = false;
  constructor(private router: Router, private jwtService: JwtService) {}

  ngOnInit(): void {}

  loginHandler() {
    console.log('hi');
    this.router.navigateByUrl('sign-in');
  }
  logOutHandler() {
    this.jwtService.destroyToken();
    this.router.navigateByUrl('sign-in');
  }
}
