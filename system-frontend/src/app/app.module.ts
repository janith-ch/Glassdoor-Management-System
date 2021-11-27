import { DrawerComponent } from './home/drawer/drawer.component';
import { HomeModule } from './home/home.module';
import { AuthModule } from './auth/auth.module';
import { RouterModule } from '@angular/router';
import { JwtService } from './service/jwt.service';
import { MainService } from './service/main.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthService } from './service/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AuthModule,
    HomeModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [AuthService, MainService, JwtService],
  bootstrap: [AppComponent],
})
export class AppModule {}
