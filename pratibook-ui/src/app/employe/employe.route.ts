import {Routes} from "@angular/router";

export const employeRoute: Routes = [
  {
    path: 'reservation',
    loadComponent: () => import('./liste-reservation/liste-reservation.component').then(mod => mod.ListeReservationComponent)
  },
  {
    path: 'emprunter',
    loadComponent: () => import('./emprunter/emprunter.component').then(mod => mod.EmprunterComponent)
  },
  {
    path: 'rendu',
    loadComponent: () => import('./rendu/rendu.component').then(mod => mod.RenduComponent)
  },
  {
    path: 'stock/ajouter-auteur',
    loadComponent: () => import('./auteur-form/auteur-form.component').then(mod => mod.AuteurFormComponent)
  },
  {
    path: 'stock/ajouter-oeuvre',
    loadComponent: () => import('./oeuvre-form/oeuvre-form.component').then(mod => mod.OeuvreFormComponent)
  }
]
