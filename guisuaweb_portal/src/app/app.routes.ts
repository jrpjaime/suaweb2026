import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { LayoutComponent } from './shared/layout/layout.component';
import { HomeComponent } from './business/home/home.component';
import { LoginComponent } from './business/authentication/login/login.component';
import { AuthGuard } from './core/guards/auth.guard';
import { AuthenticatedGuard } from './core/guards/authenticated.guard';
import { AcreditacionymembresiaComponent } from './business/contador/acreditacionymembresia/acreditacionymembresia.component';
import { ModificaciondatosComponent } from './business/contador/modificaciondatos/modificaciondatos.component';
import { SolicitudbajaComponent } from './business/contador/solicitudbaja/solicitudbaja.component';

import { AcreditacionymembresiaAcuseComponent } from './business/contador/acreditacionymembresia/acreditacionymembresia-acuse/acreditacionymembresia-acuse.component';
import { SolicitudbajaAcuseComponent } from './business/contador/solicitudbaja/solicitudbaja-acuse/solicitudbaja-acuse.component';
import { ModificaciondatosAcuseComponent } from './business/contador/modificaciondatos/modificaciondatos-acuse/modificaciondatos-acuse.component';
import { SolicitudpatronComponent } from './business/patron/solicitudpatron/solicitudpatron.component';
import { PatronGuard } from './core/guards/PatronGuard';




export const routes: Routes = [
  { path: 'login', component: LoginComponent },

  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], // Solo necesitamos AuthGuard aquí
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'contador/acreditacionymembresia',
        component: AcreditacionymembresiaComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'contador/acreditacionymembresiaacuse',
        component: AcreditacionymembresiaAcuseComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'contador/modificaciondatos',
        component: ModificaciondatosComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'contador/modificaciondatosacuse',
        component: ModificaciondatosAcuseComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'contador/solicitudbaja',
        component: SolicitudbajaComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'contador/solicitudbajaacuse',
        component: SolicitudbajaAcuseComponent,
        canActivate: [PatronGuard],
      },
      {
        path: 'patron/solicitudpatron',
        component: SolicitudpatronComponent,
        canActivate: [PatronGuard] // Se aplica la protección aquí
      },
      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];
