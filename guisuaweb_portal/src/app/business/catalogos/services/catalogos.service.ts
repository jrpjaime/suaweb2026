


import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoCuotaDto } from '../model/TipoCuotaDto';
import { environment } from '../../../../environments/environment';
import { RegistroPatronalDto } from '../model/RegistroPatronalDto';

@Injectable({
  providedIn: 'root'
})
export class CatalogosService {
  private readonly http = inject(HttpClient);
  private readonly urlBase = `${environment.catalogosApiUrl}/v1`;

  /**
   * Obtiene el catálogo de tipos de cuota (1=IMSS, 2=RCV)
   */
  getTiposCuota(): Observable<TipoCuotaDto[]> {
    return this.http.get<TipoCuotaDto[]>(`${this.urlBase}/tiposCuota`);
  }



  /**
   * Consulta los registros patronales vinculados al RFC del usuario.
   */
  consultarRegistrosPatronales(request: { rfc: string }): Observable<RegistroPatronalDto[]> {
    const url = `${this.urlBase}/consultarRegistrosPatronales`;
    return this.http.post<RegistroPatronalDto[]>(url, request);
  }



}
