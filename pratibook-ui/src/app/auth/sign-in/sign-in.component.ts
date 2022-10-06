import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../shared/service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  formLogin: FormGroup = new FormGroup<any>({
    email: new FormControl<string>('baudu2@test.com', [Validators.required, Validators.email]),
    password: new FormControl<string>('testPassword', [Validators.required])
  })

  constructor(private userService: UserService, private route: Router) { }

  ngOnInit(): void {
    this.userService.connected.subscribe((res) => {
      if (!res)
        return
      this.route.navigate(['/'])
    })
  }

  valid() {
    const email: string = this.formLogin.get('email')?.value
    const password: string = this.formLogin.get('password')?.value
    this.userService.loginUser(email, password).then(m => console.log(m))
  }
}
