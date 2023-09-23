import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from '../config/app-constants';
import { Boleta } from '../model/boleta.model';
import { Calificacion } from '../model/calificacion.model';
import { OveralResponse } from '../model/overal-response.model';

@Injectable({
  providedIn: 'root'
})
export class CalificacionesService {

  constructor(
    private httpClient: HttpClient,
    private appConstants: AppConstants  
  ) { }

  public getBoleta(idAlumno: number): Observable<Boleta>{
    return this.httpClient.get<Boleta>(
      this.appConstants.URL_CALIFICAICONES + '?idAlumno=' + idAlumno,
    );
  }

  public addCalificacion(calificacion: Calificacion): Observable<OveralResponse>{
    return this.httpClient.post<OveralResponse>(
      this.appConstants.URL_CALIFICAICONES,
      calificacion
    );
  }

  public updCalificacion(calificacion: Calificacion): Observable<OveralResponse>{
    return this.httpClient.put<OveralResponse>(
      this.appConstants.URL_CALIFICAICONES,
      calificacion
    );
  }

  public delCalificacion(idCalificacion: number): Observable<OveralResponse>{
    return this.httpClient.delete<OveralResponse>(
      this.appConstants.URL_CALIFICAICONES + '?idCalificacion=' + idCalificacion
    );
  }
}
