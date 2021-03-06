import { Component, OnInit } from '@angular/core';
import { User } from "../user";
import { UserService } from "../user.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

 public users: User[];
 
  constructor(private router: Router,private userService: UserService) { }

   ngOnInit() { //when component loading get all users and set the users[]
    this.getAllUsers();
  }

  
  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.users = users;
      },
      err => {
        console.log(err);
      }
 
    );
  }
  
  
  redirectNewUserPage() {
    this.router.navigate(['/user/create']);
  }
 
  editUserPage(user: User) {
    if (user) {
      this.router.navigate(['/user/edit', user.id]);
    }
  }
 
  deleteUser(user: User) {
    if (user) {
      this.userService.deleteUserById(user.id).subscribe(
        status => {
         
          for (var i = 0; i < this.users.length; i++) {
            if (this.users[i].id == user.id) {
              this.users.splice(i, 1);
            }
          }
        },
        err => {
          console.log(err);
        }
      );
      
    }
  }
}
