export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
  phoneNr: string;
  town: Town;
}

export interface Town {
  id: number;
  name: string;
  messages: Message[];
}

export interface Message {
  id: number;
  title: string;
  text: string;
  townId: number;
  userId: number;
}
