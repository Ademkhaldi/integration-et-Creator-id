import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatasourceService } from '../service/datasource.service';
import { Datasource } from '../datasource.model';

@Component({
  selector: 'app-update-datasource',
  templateUrl: './update-datasource.component.html',
  styleUrls: ['./update-datasource.component.scss']
})
export class UpdateDatasourceComponent implements OnInit {

  id: string = '';
  datasource: Datasource = new Datasource();
  updator_id: string = 'updator'; // Nouveau champ updator_id
  passwordFieldType: string = 'password'; // Field type for password input
  passwordMaxLength:number = 8
  constructor(private route: ActivatedRoute, private router: Router,
              private datasourceService: DatasourceService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.datasourceService.retrieveDatasource(this.id)
      .subscribe(data => {
        console.log(data);
        this.datasource = data;
      }, error => console.log(error));
  } 

  updateDatasource() {
// Vérification de Type
    if (this.datasource.type.length === 0 ) {
      this.showErrorMessage('type', "Type ne peut pas être vide");
      return;
    }
    

    

    // connection_port
    if (this.datasource.connection_port.toString().length > 4) {
      this.showErrorMessage('connection_port', "connection_port ne peut pas depasser 4 caractere");
      return;
    }
    if (this.datasource.connection_port === 0) {
      this.showErrorMessage('connection_port', "connection_port ne peut pas etre egal a 0");
      return;
    }
    
// Vérification de l'URL
if (!this.datasource.url.trim()) {
  this.showErrorMessage('url', "L'URL ne peut pas être vide.");
  return;
}


     // Vérification d'index
     if (!this.isValidIndex(this.datasource.index)) {
      if (this.datasource.index === 0) {
        this.showErrorMessage('index', "L'index ne peut pas être égal à zéro");
      } else {
        this.showErrorMessage('index', "L'index ne peut pas être négatif");
      }
      return;
    }        

// Vérification de User
if (this.datasource.user.length === 0 ) {
  this.showErrorMessage('user', "User ne peut pas être vide");
  return;
}

    
// Vérification de Password
if (this.datasource.password.length === 0 ) {
  this.showErrorMessage('password', "password ne peut pas être vide");
  return;
}
if (this.datasource.password.length > 8 ) {
  this.showErrorMessage('password', "password ne peut pas depasser 8 caracteres");
  return;
}


    const updateData = {
      ...this.datasource, // Copier toutes les autres propriétés du tableau de bord
      updator_id: this.updator_id // Ajouter l'updator_id
    };
  
    this.datasourceService.updateDatasource(this.id, updateData).subscribe(
      (data) => {
        console.log(data);
        this.gotoList();
      },
      (error) => {
        console.log(error);
      }
    );
  }
  isValidIndex(index: number): boolean {
    return typeof index === 'number' && index >= 1;
  }
// Méthode pour afficher un message d'erreur sous le champ correspondant
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
    this.updateDatasource();
  }

  gotoList() {
    this.router.navigate(['/getAllDatasources']);
  }

  togglePasswordVisibility(): void {
    // Toggle password field type between 'password' and 'text'
    this.passwordFieldType = (this.passwordFieldType === 'password') ? 'text' : 'password';
  }
  
  cancelUpdate() {
    this.gotoList(); // Naviguer vers la liste des tableaux de bord
  }
}
