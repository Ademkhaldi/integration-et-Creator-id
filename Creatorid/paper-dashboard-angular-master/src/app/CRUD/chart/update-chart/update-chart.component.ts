import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chart } from '../chart.model';
import { charttypeLabelMapping, charttype } from '../charttype.model';
import { ChartService } from '../service/chart.service';

@Component({
  selector: 'app-update-chart',
  templateUrl: './update-chart.component.html',
  styleUrls: ['./update-chart.component.scss']
})
export class UpdateChartComponent implements OnInit {
  public charttypeLabelMapping = charttypeLabelMapping;
  public Charttypes = Object.values(charttype);
  
  id: string = '';
  chart: Chart = new Chart();
  updator_id: string = 'updator'; // Nouveau champ updator_id
  constructor(private route: ActivatedRoute, private router: Router,
              private chartService: ChartService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.chartService.retrieveChart(this.id)
      .subscribe(data => {
        console.log(data);
        this.chart = data;
      }, error => console.log(error));
  } 

  updateChart() {

    if (this.chart.title.length === 0 ) {
      this.showErrorMessage('title', "title ne peut pas être vide");
      return;
    }
    

    if (this.chart.x_axis.length === 0 ) {
      this.showErrorMessage('x_axis', "x_axis ne peut pas être vide");
      return;
    }
   
    if (this.chart.y_axis.length === 0 ) {
      this.showErrorMessage('y_axis', "y_axis ne peut pas être vide");
      return;
    }



    const updateData = {
      ...this.chart, // Copier toutes les autres propriétés du tableau de bord
      updator_id: this.updator_id // Ajouter l'updator_id
    };
  
    this.chartService.updateChart(this.id, updateData).subscribe(
      (data) => {
        console.log(data);
        this.gotoList();
      },
      (error) => {
        console.log(error);
      }
    );
  }
  showErrorMessage(inputId: string, message: string): void {
    const inputElement = document.getElementById(inputId);
    const errorDiv = inputElement.nextElementSibling;
    if (errorDiv && errorDiv.classList.contains('text-danger')) {
      errorDiv.textContent = message;
    } else {
      const div = document.createElement('div');
      div.textContent = message;
      div.classList.add('text-danger');
      inputElement.insertAdjacentElement('afterend', div);
    }
  }
  onSubmit() {
    this.updateChart();
  }

  gotoList() {
    this.router.navigate(['/getAllCharts']);
  }
  cancelUpdateChart() {
    this.gotoList(); // Naviguer vers la liste des tableaux de bord
  }
}


