import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  public dataModel: FormGroup;
  hide = true;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authservice: AuthService
  ) {
    this.dataModel = this.formBuilder.group({
      name: [null, Validators.required],
      email: [null, Validators.required],
      userName: [null, Validators.required],
      password: [null, Validators.required],
      phone: [null, Validators.required],
      occupation: [null, Validators.required],
    });
  }
  ngOnInit(): void {}

  get user() {
    return this.dataModel.controls;
  }
  signIn() {
    this.router.navigateByUrl('/sign-in');
  }

  signUp() {
    const userRole = {
      id: 2,
      roleName: null,
      permission: null,
    };
    const user = {
      name: this.dataModel.value.name,
      email: this.dataModel.value.email,
      userName: this.dataModel.value.userName,
      password: this.dataModel.value.password,
      phone: this.dataModel.value.phone,
      occupation: this.dataModel.value.occupation,
      userRole: userRole,
    };
    console.log(user);
    this.authservice.userSignUp(user).subscribe(
      (data) => {
        console.log(data);
        this.dataModel.reset();
        this.router.navigateByUrl('sign-in');
      },
      () => {
        alert('user Registration faild !!!');
      }
    );
  }
}
