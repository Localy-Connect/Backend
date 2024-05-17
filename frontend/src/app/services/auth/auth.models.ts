export interface AuthResponse {
  token: string;
}

export interface User {
  id: number;
  name: string;
  email: string;
  phoneNr: string | null;
  town: { id: number, name: string } | null;
}

export interface SigninResponse extends AuthResponse {
  user: User;
}

export interface SignupResponse {
  message: string;
}
