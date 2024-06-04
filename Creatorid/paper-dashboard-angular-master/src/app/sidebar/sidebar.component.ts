import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { filter, map } from 'rxjs';


export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    children?: RouteInfo[]; // Ajoutez une propriété pour les sous-menus

}

export const ROUTES: RouteInfo[] = [                  
    { path: '/dashboard',     title: 'Statistics',         icon:'nc-icon nc-chart-bar-32',       class: '' },
    
    { path: '/getAllDashboards',     title: 'Dashboards',         icon:'nc-icon nc-chart-pie-36',       class: '' },
    { path: '/getAllPortlets',     title: 'Portlets',         icon:'nc-icon nc-box-2',       class: '' },
    { path: '/getAllDatasources',     title: 'Datasources',         icon:'nc-icon nc-cloud-upload-94',       class: '' },
    
    { path: '/getAllCharts',     title: 'Charts',         icon:'nc-icon nc-vector',       class: '' },
    { path: 'dashbord/getPortletsForDashboard/:dashboardId',     title: 'Assign',         icon:'nc-icon nc-globe',       class: '' },
 
    
    
    { path: '/icons',         title: 'Icons',             icon:'nc-diamond',    class: '' },
    { path: '/maps',          title: 'Maps',              icon:'nc-pin-3',      class: '' },
    //{ path: '/notifications', title: 'Notifications',     icon:'nc-bell-55',    class: '' },
    { path: '/user',          title: 'User Profile',      icon:'nc-single-02',  class: '' },
    { path: '/table',         title: 'Table List',        icon:'nc-tile-56',    class: '' },
    { path: '/typography',    title: 'Typography',        icon:'nc-caps-small', class: '' },
    { path: '/upgrade',       title: 'Upgrade to PRO',    icon:'nc-spaceship',  class: 'active-pro' },

    { path: '/user-list',          title: 'user-list ',      icon:'nc-single-02',  class: '' },




];

@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
}
