import {Genre} from "./igenre";
import {Oeuvre} from "./oeuvre";

export interface IAuteur {
  idAuteur: number | undefined,
  nomAuteur: string,
  prenomAuteur: string | null,
  dateNaissanceAuteur: Date | null,
  dateDecesAuteur: Date | null,
  genres: Genre[],
}

export interface AuteurWithOeuvres extends IAuteur {
  idAuteur: number;
  oeuvres: Oeuvre[];
}

export class Auteur implements IAuteur {

  constructor(nom: string, prenom: string | null, dateNaissance: Date | null, dateDeces: Date | null) {
    this.nomAuteur = nom;
    this.prenomAuteur = prenom;
    this.dateNaissanceAuteur = dateNaissance;
    this.dateDecesAuteur = dateDeces;
  }

  idAuteur: number | undefined;
  nomAuteur: string;
  prenomAuteur: string | null;
  dateDecesAuteur: Date | null;
  dateNaissanceAuteur: Date | null;
  genres: Genre[] = [];
}
