import {IGenre} from "./igenre";

export interface IAuteur {
  idAuteur: number | undefined,
  nomAuteur: string,
  prenomAuteur: string,
  dateNaissanceAuteur: Date,
  dateDecesAuteur: Date | undefined,
  genres: IGenre[],
}
