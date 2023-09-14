import { Component } from '@angular/core';
import { OffreDto } from '../model/OffreDto';
import { OffreService } from '../Services/offre.service';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent {
  selectedOffre: OffreDto = new OffreDto;
  listoffre: OffreDto[] = [];
  
  
  constructor(private os: OffreService) { }

  ngOnInit(): void {
    this.getAll();
  }


  getAll() {
    this.os.getAll().subscribe((response) => {
      this.listoffre = response;
    });
  }

  add(dto: OffreDto) {
    this.os.add(dto).subscribe(
      (response) => {
        this.getAll();
        console.log(response);
      });
  }
  delete(dto: OffreDto) {
    this.os.delete(dto).subscribe(
      (response) => {
        this.getAll();
        console.log('deleted', response);
      }
    );
  }
  update(dto: OffreDto) {
    this.os.update(dto).subscribe(
      (response) => {
        this.getAll(); // Refresh the carrier list after updating
        console.log('updated', response);
      }
    );
  }
}
