import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {UserService} from "../../shared/service/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SignUpRequest, SignUpRequestConstructor} from "../../shared/model/signUpRequest";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  formSignUp: FormGroup = new FormGroup<any>({
    email: new FormControl<string>('', [Validators.required, Validators.email, Validators.maxLength(50)]),
    password: new FormControl<string>('', [Validators.required, Validators.minLength(8)]),
    validPassword: new FormControl<string>('', [Validators.required, Validators.minLength(8), this.passwordValidator()]),
    nom: new FormControl<string>('', [Validators.required, Validators.maxLength(50)]),
    prenom: new FormControl<string>('', [Validators.required, Validators.maxLength(50)]),
    adresse: new FormControl<string>('', [Validators.required, Validators.maxLength(50)]),
    codePostal: new FormControl<string>('', [Validators.required, this.codePostalValidator()]),
    ville: new FormControl<string>('', [Validators.required, Validators.maxLength(50)]),
  })

  constructor(private userService: UserService, private snackar: MatSnackBar, private route: Router) {
  }

  ngOnInit(): void {
  }

  private codePostalValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const valid = control.value.length === 5 && !isNaN(+control.value)
      return !valid ? {invalidFormat: {value: control.value}} : null;
    };
  }

  private passwordValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!this.formSignUp)
        return null
      const passwordValue: string = this.formSignUp.controls['password'].value
      const validPassword: string = this.formSignUp.controls['validPassword'].value
      return passwordValue !== validPassword ? {notExactPassword: {value: control.value}} : null;
    };
  }

  valid() {
    const registerRequest: SignUpRequest = new SignUpRequestConstructor(this.formSignUp.value)
    this.userService.register(registerRequest).subscribe({
      next: (res) => {
        if (!res.ok) {
          this.snackar.open("Erreur lors de l'inscription", 'X', {duration: 5000})
          throw new Error('Erreur lors de l\'inscription')
        }
        this.route.navigate(['/signIn']).then(() => {
          this.snackar.open("Inscription effectue", 'X', {duration: 5000})
        })
      },
      error: (error) => {
        this.snackar.open("Erreur lors de l'inscription", 'X', {duration: 5000})
        throw new Error(error)
      }
    })
  }
}
