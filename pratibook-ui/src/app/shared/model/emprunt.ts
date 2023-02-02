export interface IEmprunt {
  codeBarre: string,
  dateReservation?: Date,
  dateLocation?: Date,
  dateRenduTheorique?: Date,
  dateRenduReel?: Date,
  etatPhysiqueLocation?: number,
  etatPhysiqueRendu?: number
}
