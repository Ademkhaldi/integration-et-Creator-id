import { Component, OnInit } from '@angular/core';
import { Dashboard } from 'app/CRUD/dashboard/dashboard.model';
import { Portlet } from '../../portlet.model';
import { DashboardService } from 'app/CRUD/dashboard/service/dashboard.service';
import { PortletService } from '../../service/portlet.service';

@Component({
  selector: 'app-portlet-dashboard-assignment',
  templateUrl: './portlet-dashboard-assignment.component.html',
  styleUrls: ['./portlet-dashboard-assignment.component.scss']
})
export class PortletDashboardAssignmentComponent implements OnInit {

  portlets: Portlet[] = [];
  dashboard: Dashboard[] = [];
  selectedPortletId: string | null = null;
  selectedDashboardId: string | null = null;


  constructor(
    private portletService: PortletService,
    private dashboardService: DashboardService
  ) {}

  ngOnInit(): void {
    this.loadPortlets();
    this.loadDashboards();
  }

  loadPortlets() {
    this.portletService.getAllPortlets().subscribe(data => {
      this.portlets = data;
    });
  }

  loadDashboards() {
    this.dashboardService.getAllDashboards().subscribe(data => {
      this.dashboard = data;
    });
  }

  affecterDashboardAPortlet() {
    if (this.selectedPortletId !== null && this.selectedDashboardId !== null) {
      this.portletService.affecterDashboardAPortlet(this.selectedPortletId, this.selectedDashboardId)
        .subscribe(() => {
          // Actualizez la liste des portlet et des Dashboard après l'affectation réussie
          this.loadPortlets();
          this.loadDashboards();
          // Réinitialisez les sélections après l'affectation réussie
          this.selectedPortletId = null;
          this.selectedDashboardId = null;

          // Chargez les Dashboard associés à la portlet
        //  this.loadPortletDashboards(this.selectedPortletId);
        },
        error => console.log(error)
      );
    }
  }}
