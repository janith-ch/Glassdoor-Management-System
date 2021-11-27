import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/service/jwt.service';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css'],
})
export class DrawerComponent implements OnInit {
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
