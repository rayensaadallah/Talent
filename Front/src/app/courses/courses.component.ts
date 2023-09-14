import { Component } from '@angular/core';
import { CarrierDto } from '../model/CarrierDto';
import { CarrierService } from '../Services/carrier.service';
import { FormationDto } from '../model/FormationDto';
import { FormationService } from '../Services/formation.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {

  selectedformation: FormationDto = new FormationDto;
  listCarrier: FormationDto[] = [];

  constructor(private fs:FormationService) { }

  ngOnInit(): void {
    this.getAll();
  }
  
  getAll() {
    this.fs.getAll().subscribe((response) => {
      this.listCarrier= response;
    });
  }
  
  add(f: FormationDto) {
    this.fs.add(f).subscribe(
      (product) => {
        this.getAll();
        console.log(product);
      },
      (error) => {
        console.error('Failed to add ', error);
      }
    );
  }
  delete(f: FormationDto) {
    this.fs.delete(f).subscribe(
      (response) => {
        this.getAll();
        console.log('deleted', response);
      },
      (error) => {
        console.error('Failed to delete ', error);
      }
    );
  }
  buy(): void {
    this.fs.buyFormation(this.selectedformation).subscribe(
      (response) => {
        console.log(response); // Handle the response as needed
      },
      (error) => {
        console.error(error); // Handle any errors
      }
    );
  }
}
