export class User {
  id: number;
  name: string;
  email: string;
  pwd:string;
 address:string;
 
  constructor(id: number, name: string, pwd: string, email: string, address: string){
    this.id = id;
    this.name = name;
	this.pwd = pwd;
    this.email = email;
	this.address=address;
  }
 
}