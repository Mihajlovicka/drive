

export interface UserI{
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  birthday: Date|undefined;
}


export class User implements UserI{
  email: string ='';
  password: string ='';
  firstName: string ='';
  lastName: string ='';
  birthday: Date| undefined ;
}
