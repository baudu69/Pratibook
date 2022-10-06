import {Routes} from "@angular/router";
import {AccueilComponent} from "./accueil/accueil.component";

export const appRoutes: Routes = [
  { path: '', component: AccueilComponent },
  { path: 'signIn', loadChildren: () => import('./auth/sign-in/sign-in.module').then(mod => mod.SignInModule) },
  { path: 'signUp', loadChildren: () => import('./auth/sign-up/sign-up.module').then(mod => mod.SignUpModule) }
]
