import {Genre} from "./igenre";

export interface IOeuvre {
  idOeuvre: number | undefined;
  titreOeuvre: string;
  anneeSortie: number;
  genres: Genre[];
}

export interface Oeuvre extends IOeuvre {
  idOeuvre: number;
}
