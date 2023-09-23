import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Alumno } from './model/alumno.model';
import { BoletaRegistro } from './model/boleta-registro.model';
import { Boleta } from './model/boleta.model';
import { Calificacion } from './model/calificacion.model';
import { Materia } from './model/materia.model';
import { AlumnosService } from './services/alumnos.service';
import { CalificacionesService } from './services/calificaciones.service';
import { MateriasService } from './services/materias.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  frmCalificacion: FormGroup;
  showFiller = false;
  lstAlumnos: Alumno[] = [];
  alumnoAct: Alumno;
  lstMaterias: Materia[] = [];
  materiaAct: Materia;
  idMateriaAct: number;
  calificacion: Calificacion;
  valCalificacion = 0;
  boleta: Boleta;
  bndNueva = true;
  displayedColumns: string[] = [
    'idCalificacion',
    'materia',
    'calificacion',
    'fecha_registro',
    'acciones',
  ];
  constructor(
    private _materiasService: MateriasService,
    private _alumnosService: AlumnosService,
    private _calificacionesService: CalificacionesService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.obtenerAlumnos();
    this.obtenerMaterias();

    // this.frmCalificacion = this.formBuilder.group({
    //   selMateria: [null],
    //   inpCalificacion: [null]
    // });
  }

  public obtenerMaterias(): void {
    this._materiasService.getAllMaterias().subscribe(
      (resp) => {
        this.lstMaterias = resp;
      },
      (error) => {
        //this.mensajeRespuesta('No es posible recuperar Alumnos');
        console.log('Error', error);
      }
    );
  }
  public obtenerAlumnos(): void {
    this._alumnosService.getAllAlumnos().subscribe(
      (resp) => {
        this.lstAlumnos = resp;
      },
      (error) => {
        //this.mensajeRespuesta('No es posible recuperar Alumnos');
        console.log('Error', error);
      }
    );
  }

  public obtenerBoleta(idAlumno: number): void {
    this._calificacionesService.getBoleta(idAlumno).subscribe(
      (resp) => {
        this.boleta = resp;
      },
      (error) => {
        //this.mensajeRespuesta('No es posible recuperar Alumnos');
        console.log('Error', error);
      }
    );
  }
  guardarCalificacion(): void {

    if (this.bndNueva) {
      let calAux: Calificacion = {
        idAlumno: this.alumnoAct.id ? this.alumnoAct.id : -1,
        idMateria: this.materiaAct.id ? this.materiaAct.id : -1,
        calificacion: this.valCalificacion,
      };
      this._calificacionesService.addCalificacion(calAux).subscribe(
        (resp) => {
          console.log(resp);
          this.limpiaCalificacion();
          this.bndNueva = true;
          if(this.alumnoAct.id)
            this.obtenerBoleta(this.alumnoAct.id);
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      let calAux: Calificacion = {
        idCalificacion: this.calificacion.idCalificacion? this.calificacion.idCalificacion: -1,
        idAlumno: this.alumnoAct.id ? this.alumnoAct.id : -1,
        idMateria: this.materiaAct.id ? this.materiaAct.id : -1,
        calificacion: this.valCalificacion,
      };
      this._calificacionesService.updCalificacion(calAux).subscribe(
        (resp) => {
          console.log(resp);
          this.limpiaCalificacion();
          this.bndNueva=true;
          if(this.alumnoAct.id)
            this.obtenerBoleta(this.alumnoAct.id);
        },
        (error) => {
          console.log(error);
        }
      );

    }
  }
  muestraCalif(registro: BoletaRegistro): void {

    this.lstMaterias.forEach((mat) => {
      if (mat.name === registro.materia) {
        this.materiaAct = mat;
      }
    });
    this.valCalificacion = registro.calificacion;
    this.bndNueva = false;
    console.log(registro);
    this.calificacion = {
      idCalificacion: registro.idCalificacion,
      idAlumno: this.alumnoAct.id ? this.alumnoAct.id : -1,
      idMateria: this.materiaAct.id ? this.materiaAct.id : -1,
      calificacion: this.valCalificacion,
    };
  }
  eliminarCalif(registro: BoletaRegistro) {
    this._calificacionesService
      .delCalificacion(registro.idCalificacion)
      .subscribe(
        (resp) => {
          console.log(resp);
          if(this.alumnoAct.id)
            this.obtenerBoleta(this.alumnoAct.id);
        },
        (error) => {
          console.log(error);
        }
      );
  }
  seleccionaAlumno(alumno: Alumno): void {
    this.alumnoAct = alumno;
    this.obtenerBoleta(this.alumnoAct.id ? this.alumnoAct.id : -1);
  }

  limpiaCalificacion(): void {
    this.materiaAct = {
      activo: 0,
      name: '',
    };
  }
}
