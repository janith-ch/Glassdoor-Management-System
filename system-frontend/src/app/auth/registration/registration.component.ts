import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  public dataModel: FormGroup;
  hide = true;
  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.dataModel = this.formBuilder.group({
      name: [null, Validators.required],
      email: [null, Validators.required],
      userName: [null, Validators.required],
      password: [null, Validators.required],
      phone: [null, Validators.required],
    });
  }
  ngOnInit(): void {}
  userRegistration() {
    const user = {
      name: this.dataModel.value.name,
      email: this.dataModel.value.email,
      username: this.dataModel.value.userName,
      password: this.dataModel.value.password,
      phone: this.dataModel.value.phone,
    };
  }

  get user() {
    return this.dataModel.controls;
  }
  signIn() {
    this.router.navigateByUrl('/sign-in');
  }
}
