import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormationDto } from '../model/FormationDto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private baseUrl = 'http://localhost:8089/formation'; 

  constructor(private http: HttpClient) {}

  getAll(): Observable<FormationDto[]> {
    return this.http.get<FormationDto[]>(`${this.baseUrl}/`);
  }
  add(dto: FormationDto): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/add`, dto);
  }
  delete(dto: FormationDto): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete`, { body: dto });
  }
  update(dto: FormationDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update`, { body: dto });
  }
  buyFormation(formation: FormationDto): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/buy`, formation);
  }


}
