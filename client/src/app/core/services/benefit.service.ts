import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BENEFIT_API = 'http://localhost:8080/api/benefits/';

@Injectable({
  providedIn: 'root'
})
export class BenefitService {

  constructor(private http: HttpClient) {}

  getMyBenefits(): Observable<any> {
    return this.http.get(BENEFIT_API + 'my-benefits');
  }

  getMyActiveBenefits(): Observable<any> {
    return this.http.get(BENEFIT_API + 'my-benefits/active');
  }

  getAllBenefits(): Observable<any> {
    return this.http.get(BENEFIT_API + 'all');
  }
}