import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-add-user-detail',
  templateUrl: './add-user-detail.component.html',
  styleUrls: ['./add-user-detail.component.css']
})
export class AddUserDetailComponent implements OnInit {

  alert:boolean= false;
  user: User = new User();
  myImage:string='assets/image/covid-19-vaccine.jpg'; 
  constructor(private router: Router, private userService: UserServiceService) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.alert=true;
    this.userService.addUser(this.user).subscribe( data =>{
      console.log(data);
      
    },
    error=> console.log(error));
  }

  onSubmit(){
    console.log(this.user); 
    this.saveUser();
  }

  closeAlert()
  {
    this.alert=false;
  }
}
