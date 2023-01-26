export interface Genre {
  idGenre: number;
  nomGenre: string;
}

export interface Auteur {
  idAuteur: number;
  nomAuteur: string;
  prenomAuteur: string;
}

export interface Oeuvre {
  idOeuvre: number;
  titre: string;
  anneeSortie: number;
  isbn: string;
  nbInstanceDisponibles: number;
  genres: Genre[];
  auteurs: Auteur[];
}
