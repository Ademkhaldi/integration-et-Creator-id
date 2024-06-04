import { Component, OnInit } from '@angular/core';
import { Chart } from '../../../chart.model';
import { Datasource } from 'app/CRUD/datasource/datasource.model';
import { ChartService } from '../../../service/chart.service';
import { DatasourceService } from 'app/CRUD/datasource/service/datasource.service';

@Component({
  selector: 'app-chart-datasource-assignment',
  templateUrl: './chart-datasource-assignment.component.html',
  styleUrls: ['./chart-datasource-assignment.component.scss']
})
export class ChartDatasourceAssignmentComponent implements OnInit {

  charts: Chart[] = [];
  datasource: Datasource[] = [];
  selectedChartId: string | null = null;
  selectedDatasourceId: string | null = null;


  constructor(
    private chartService: ChartService,
    private datasourceService: DatasourceService
  ) {}

  ngOnInit(): void {
    this.loadCharts();
    this.loadDatasources();
  }

  loadCharts() {
    this.chartService.getAllCharts().subscribe(data => {
      this.charts = data;
    });
  }

  loadDatasources() {
    this.datasourceService.getAllDatasources().subscribe(data => {
      this.datasource = data;
    });
  }

  affecterDatasourceAChart() {
    if (this.selectedChartId !== null && this.selectedDatasourceId !== null) {
      this.chartService.affecterDatasourceAChart(this.selectedChartId, this.selectedDatasourceId)
        .subscribe(() => {
          // Actualizez la liste des Chart et des datasource après l'affectation réussie
          this.loadCharts();
          this.loadDatasources();
          // Réinitialisez les sélections après l'affectation réussie
          this.selectedChartId = null;
          this.selectedDatasourceId = null;

          // Chargez les datasource associés à la Chart
        //  this.loadChartdatasources(this.selectedChartId);
        },
        error => console.log(error)
      );
    }
  }}
