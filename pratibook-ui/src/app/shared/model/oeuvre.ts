import {Genre} from "./igenre";

export interface IOeuvre {
  idOeuvre: number | undefined;
  titre: string;
  anneeSortie: number;
  genres: Genre[];
}

export interface Oeuvre extends IOeuvre {
  idOeuvre: number;
}
