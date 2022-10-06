export interface SignUpRequest {
  email: string
  password: string
  nom: string
  prenom: string
  adresse: string
  codePostal: string
  ville: string
}

export class SignUpRequestConstructor implements SignUpRequest{
  constructor(init?: Partial<SignUpRequestConstructor>) {
  Object.assign(this, init);
}

  adresse: string = '';
  codePostal: string = '';
  email: string = '';
  nom: string = '';
  password: string = '';
  prenom: string = '';
  ville: string = '';
}
