import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OffreDto } from '../model/OffreDto';
import { UserDto } from '../model/UserDto';
import { Observable } from 'rxjs';
import { CarrierDto } from '../model/CarrierDto';

@Injectable({
  providedIn: 'root'
})
export class OffreService {

  private baseUrl = 'http://localhost:8089/offer';

  constructor(private http: HttpClient) {}

  getAll(): Observable<OffreDto[]> {
    return this.http.get<OffreDto[]>(`${this.baseUrl}/`);
  }

  getOne(): Observable<OffreDto[]> {
    return this.http.get<OffreDto[]>(`${this.baseUrl}/show`);
  }

  assignToUser(carrierDto: OffreDto, dto: CarrierDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/OffreToCarrier`, { carrierDto, dto });
  }
  add(Dto: OffreDto): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/add`, Dto);
  }
  delete(Dto: OffreDto): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete`, { body: Dto });
  }
  update(Dto: OffreDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update`, { body: Dto });
  }
}
