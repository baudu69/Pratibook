import {Genre} from "./igenre";
import {Oeuvre} from "./oeuvre";

export interface IAuteur {
  idAuteur: number | undefined,
  nomAuteur: string,
  prenomAuteur: string,
  dateNaissanceAuteur: Date,
  dateDecesAuteur: Date | undefined,
  genres: Genre[],
}

export interface AuteurWithOeuvres extends IAuteur {
  idAuteur: number;
  oeuvres: Oeuvre[];
}
