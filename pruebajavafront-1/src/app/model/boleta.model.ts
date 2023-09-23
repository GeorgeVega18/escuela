import { BoletaRegistro } from "./boleta-registro.model";
import { Promedio } from "./promedio.model";

export interface Boleta{
    calificaciones: BoletaRegistro[];
    promedio: Promedio;
}