import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from '../config/app-constants';
import { Alumno } from '../model/alumno.model';

@Injectable({
  providedIn: 'root'
})
export class AlumnosService {

  constructor(
    private httpClient: HttpClient,
    private appConstants: AppConstants  
  ) { }

  public getAllAlumnos(): Observable<Alumno[]>{
    return this.httpClient.get<Alumno[]>(
      this.appConstants.URL_ALUMNOS,
    );
  }
}
