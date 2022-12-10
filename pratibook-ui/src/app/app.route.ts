import {Routes} from "@angular/router";

export const appRoutes: Routes = [
  { path: '', loadChildren: () => import('./liste-oeuvre/liste-oeuvre.module').then(mod => mod.ListeOeuvreModule) },
  { path: 'signIn', loadChildren: () => import('./auth/sign-in/sign-in.module').then(mod => mod.SignInModule) },
  { path: 'signUp', loadChildren: () => import('./auth/sign-up/sign-up.module').then(mod => mod.SignUpModule) }
]
