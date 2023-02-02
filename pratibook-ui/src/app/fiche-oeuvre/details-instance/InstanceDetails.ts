export interface InstanceDetails {
  codeBarre: string;
  etat: Etat;
  disponibilite: Disponibilite;
  nomReserveur?: string;
  prenomReserveur?: string;
  dateRenduTheorique?: Date;
}

export enum Etat {
  Neuf = 0,
  Bon = 1,
  Abime = 2,
  Reparation = 3,

  to
}

export enum Disponibilite {
  IDLE = 0,
  DISPONIBLE = 1,
  EMPRUNTE = 2,
  PERDU = 3,
  SUPPRIME = 4,
}
