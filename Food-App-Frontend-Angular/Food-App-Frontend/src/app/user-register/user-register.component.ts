import { Component } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { UserDto } from '../dto/UserDto';
import { FormValidationService } from '../Validation/form-validation.service';
import { UserRegisterService } from './user-register.service';
import { JsonPipe } from '@angular/common';
@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

  sub!: Subscription;
  errorMessage: string = "";
  errorOccured: boolean = false;
  isSuccess: boolean = false;
  successMessage: string = "";   // FIXED SPELLING

  profileForm = new FormGroup(
    {
      password: new FormControl('', [Validators.required]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    [this.formValidationService.MatchValidator('password', 'confirmPassword')]
  );

  get passwordMatchError() {
    return (
      this.profileForm.getError('mismatch') &&
      this.profileForm.get('confirmPassword')?.touched
    );
  }

  constructor(
      private createCompanyService: UserRegisterService, 
      private formValidationService: FormValidationService
  ) {}

  onClickSubmit(createUserForm: NgForm) {
    this.errorOccured = false;
    this.errorMessage = "";

    const user = this.buildObject(createUserForm);
    if (!user) return;

    this.sub = this.createCompanyService.createUser(user).subscribe({
      next: (response) => {
        this.errorOccured = false;
        this.errorMessage = "";
        this.isSuccess = true;
        this.successMessage = JSON.stringify(response);  // <-- To avoid Type mismatch
        console.log(response);
      },
      error: (err) => {
        this.isSuccess = false;
        this.successMessage = "";

        if (err.status == 404) {
          this.errorOccured = true;
          this.errorMessage = "404 Not Found Error";
        } else if (err.status == 500) {
          this.errorOccured = true;
          this.errorMessage = "Username already taken";
        } else {
          this.errorOccured = true;
          this.errorMessage = "Some Error occurred";
        }
      }
    });
 }

 private buildObject(createUserForm: NgForm): UserDto | false {
  const userDetails = createUserForm.value;

  const user: UserDto = new UserDto();
  user.firstName = userDetails.username;  // map username → firstName
  user.lastName = "";                     // backend requires lastName
  user.email = userDetails.email;
  user.password = userDetails.password;

  return user;
}

}
