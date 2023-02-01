import {Routes} from "@angular/router";
import {ListeAuteursComponent} from "./auteur/liste-auteurs/liste-auteurs.component";

export const appRoutes: Routes = [
  {path: 'auteurs', component: ListeAuteursComponent},
  {path: '', loadChildren: () => import('./liste-oeuvre/liste-oeuvre.module').then(mod => mod.ListeOeuvreModule)},
  {path: 'signIn', loadChildren: () => import('./auth/sign-in/sign-in.module').then(mod => mod.SignInModule)},
  {path: 'signUp', loadChildren: () => import('./auth/sign-up/sign-up.module').then(mod => mod.SignUpModule)},
  {path: 'employe', loadChildren: () => import('./employe/employe.module').then(mod => mod.EmployeModule)},
]
