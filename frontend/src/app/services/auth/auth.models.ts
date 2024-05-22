import {User} from "../../model/model";

export interface AuthResponse {
  token: string;
}

export interface SigninResponse extends AuthResponse {
  user: User;
}

export interface SignupResponse {
  message: string;
}
