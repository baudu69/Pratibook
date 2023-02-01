import {Oeuvre} from "src/app/liste-oeuvre/Oeuvre";

export interface DemandeReservation {
  oeuvreDTO: Oeuvre;
  idUser: number;
  dateDemande: Date;
}
