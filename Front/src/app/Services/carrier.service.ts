import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, filter, map } from 'rxjs';
import { CarrierDto } from '../model/CarrierDto';
import { UserDto } from '../model/UserDto';


@Injectable({
  providedIn: 'root'
})
export class CarrierService {
  private baseUrl = 'http://localhost:8089/carriers';

  constructor(private http: HttpClient) {}

  getAllCarriers(): Observable<CarrierDto[]> {
    return this.http.get<CarrierDto[]>(`${this.baseUrl}/`);
  }

  assignCarrierToUser(carrierDto: CarrierDto, userDto: UserDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/assignCarrierToUser`, { carrierDto, userDto });
  }
  addCarrier(carrierDto: CarrierDto): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/add`, carrierDto);
  }
  deleteCarrier(carrierDto: CarrierDto): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete`, { body: carrierDto });
  }
  updateCarrier(carrierDto: CarrierDto): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update`, { body: carrierDto });
  }
}
