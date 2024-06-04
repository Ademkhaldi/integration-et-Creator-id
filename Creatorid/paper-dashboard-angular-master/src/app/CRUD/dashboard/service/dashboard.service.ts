import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dashboard } from 'app/CRUD/dashboard/dashboard.model';
import { Portlet } from 'app/CRUD/portlet/portlet.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  apiUrl = 'http://localhost:8099/dashboards'; // Replace with your Spring Boot API URL

  constructor(private http: HttpClient) { }

  getAllDashboards(): Observable<Dashboard[]> {
    return this.http.get<Dashboard[]>(this.apiUrl+"/getAllDashboards");
  }

  retrieveDashboard(id: String): Observable<Dashboard> {
    return this.http.get<Dashboard>(`${this.apiUrl}/${id}`);
  }

  createDashboard(dashboard: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/Add`, dashboard);
  }

  updateDashboard(id: String,dashboard: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/Update/${id}`, dashboard);
  }

  deleteDashboard(id: any): Observable<any> {
    return this.http.delete(`${this.apiUrl}/Delete/${id}`, { responseType: 'text' });

  }

  deleteAllDashboards(): Observable<any> {
    return this.http.delete(this.apiUrl+"/deleteAllDashboards",{ responseType: 'text' });
  }

  assignerListePortletsADashboard(idDashboard: string, portlets: Portlet[]): Observable<Dashboard> {
    return this.http.post<Dashboard>(`${this.apiUrl}/${idDashboard}/assignerListePortletsADashboard`, portlets);
  }

  getPortletsForDashboard(dashboardId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/getPortletsForDashboard/${dashboardId}`);
  }  

}
