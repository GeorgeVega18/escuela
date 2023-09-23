import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from '../config/app-constants';
import { Profesor } from '../model/Profesores.model';



@Injectable({
  providedIn: 'root'
})
export class ProfesorService {

  constructor(
    private httpClient: HttpClient,
    private appConstants: AppConstants
  ) { }

  public getAllAlumnos(): Observable<Profesor[]> {
    return this.httpClient.get<Profesor[]>(
      this.appConstants.URL_Profesor
    );
  }
}
