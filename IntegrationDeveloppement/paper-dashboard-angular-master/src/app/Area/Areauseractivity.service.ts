import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AreaUseractivityService {

  private baseUrl = 'http://localhost:8099/api/user-activity-data/Area';

  constructor(private http: HttpClient) { }

  getAreaUserActivityData(): Observable<any> {
    return this.http.get<any>(this.baseUrl+'/retrieve/Area');
  }


}
