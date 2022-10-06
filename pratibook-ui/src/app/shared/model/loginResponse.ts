export interface LoginResponse {
  jwt: string,
  roles: string[],
  exp: Date
}
