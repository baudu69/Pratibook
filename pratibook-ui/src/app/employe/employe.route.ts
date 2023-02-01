import {Routes} from "@angular/router";

export const employeRoute: Routes = [
  {
    path: 'reservation',
    loadComponent: () => import('./liste-reservation/liste-reservation.component').then(mod => mod.ListeReservationComponent)
  }
]
