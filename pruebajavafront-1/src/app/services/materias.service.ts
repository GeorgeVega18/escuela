import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from '../config/app-constants';
import { Materia } from '../model/materia.model';

@Injectable({
  providedIn: 'root'
})
export class MateriasService {

  constructor(
    private httpClient: HttpClient,
    private appConstants: AppConstants  
  ) { }

  public getAllMaterias(): Observable<Materia[]>{
    return this.httpClient.get<Materia[]>(
      this.appConstants.URL_MATERIAS,
    );
  }

}
