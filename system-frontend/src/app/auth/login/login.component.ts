import { Router, RouterModule } from '@angular/router';
import { JwtService } from '../../service/jwt.service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public dataModel: FormGroup;
  hide = true;
  constructor(
    private formBuilder: FormBuilder,
    private jwtService: JwtService,
    private loginService: LoginService,
    private router: Router
  ) {
    this.dataModel = this.formBuilder.group({
      userName: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  ngOnInit(): void {}
  login() {
    const credintials = {
      username: this.dataModel.value.userName,
      password: this.dataModel.value.password,
    };
    console.log(credintials);
    this.loginService.attemptLogin(credintials).subscribe(
      (data) => {
        this.jwtService.saveToken(data.accessToken);
        this.router.navigateByUrl('');
      },
      () => {
        alert('invalid username or password');
      }
    );
  }
  get credintials() {
    return this.dataModel.controls;
  }

  forgetPassword() {
    alert('please remind it again');
  }
  createAccount() {
    this.router.navigateByUrl('/sign-up');
  }
}
