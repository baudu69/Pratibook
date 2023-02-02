import {Routes} from "@angular/router";
import {ListeAuteursComponent} from "./auteur/liste-auteurs/liste-auteurs.component";
import {EmployeGuard} from "./shared/guard/employe.guard";

export const appRoutes: Routes = [
  {path: '', redirectTo: 'oeuvres', pathMatch: 'full'},
  {path: 'auteurs', component: ListeAuteursComponent},
  {
    path: 'employe',
    loadChildren: () => import('./employe/employe.module').then(mod => mod.EmployeModule),
    canActivate: [EmployeGuard]
  },
  {
    path: 'fiche-auteur',
    loadChildren: () => import('./auteur/fiche-auteur/fiche-auteur.module').then(mod => mod.FicheAuteurModule)
  },
  {
    path: 'oeuvres',
    loadChildren: () => import('./liste-oeuvre/liste-oeuvre.module').then(mod => mod.ListeOeuvreModule)
  },
  {path: 'signIn', loadChildren: () => import('./auth/sign-in/sign-in.module').then(mod => mod.SignInModule)},
  {path: 'signUp', loadChildren: () => import('./auth/sign-up/sign-up.module').then(mod => mod.SignUpModule)},
]
