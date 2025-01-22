// src/app/models/user.model.ts
export interface User {
    id: number;
    email: string;
    username:string;
    firstName: string;
    lastName: string;
    role: 'HR' | 'EMPLOYEE';
    password?: string;
    token?: string;
  }