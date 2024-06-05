import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../service/dashboard.service';
import { Router } from '@angular/router';
import { Portlet } from 'app/CRUD/portlet/portlet.model';
import { Dashboard } from '../../dashboard.model';
import { PortletService } from 'app/CRUD/portlet/service/portlet.service';

@Component({
  selector: 'app-assign-portlets',
  templateUrl: './assign-portlets.component.html',
  styleUrls: ['./assign-portlets.component.scss']
})
export class AssignPortletsComponent implements OnInit {
  portlets: Portlet[] = [];
  dashboards: Dashboard[] = [];
  selectedDashboardId: string | null = null;
  selectedPortlet: Portlet = new Portlet(); // Portlet sélectionné
  id: string;

  constructor(private dashboardService: DashboardService, private router: Router,private portletService:PortletService) { }

  ngOnInit(): void {
    this.loadDashboards();
    this.loadPortlets();
  }

  loadDashboards() {
    this.dashboardService.getAllDashboards().subscribe(data => {
      this.dashboards = data;
    });
  }

  loadPortlets() {
    this.portletService.getAllPortlets().subscribe(data => {
      this.portlets = data;
    });

  }

  assignerListePortletsADashboard(): void {
    if (!this.selectedDashboardId) {
      console.error('No dashboard selected.');
      return;
    }

    // Call the service to assign portlets to the dashboard
    this.dashboardService.assignerListePortletsADashboard(this.selectedDashboardId, [this.selectedPortlet]).subscribe({
      next: (dashboard) => {
        console.log('Portlets assigned successfully to dashboard:', dashboard);
        // Redirect to the assigned dashboard
        this.router.navigate(['dashbord/getPortletsForDashboard', this.selectedDashboardId]);
      },
      error: (error) => {
        console.error('Error assigning portlets to dashboard:', error);
        // Handle error here
      }
    });
  }

  
}
