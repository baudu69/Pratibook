import {Routes} from "@angular/router";
import {AccueilComponent} from "./accueil/accueil.component";
import {ListeAuteursComponent} from "./auteur/liste-auteurs/liste-auteurs.component";

export const appRoutes: Routes = [
  { path: '', component: AccueilComponent },
  {path: 'auteurs', component: ListeAuteursComponent},
  { path: 'signIn', loadChildren: () => import('./auth/sign-in/sign-in.module').then(mod => mod.SignInModule) },
  { path: 'signUp', loadChildren: () => import('./auth/sign-up/sign-up.module').then(mod => mod.SignUpModule) }
]
