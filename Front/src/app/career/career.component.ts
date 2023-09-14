import { Component } from '@angular/core';
import { CarrierDto } from '../model/CarrierDto';
import { CarrierService } from '../Services/carrier.service';

@Component({
  selector: 'app-career',
  templateUrl: './career.component.html',
  styleUrls: ['./career.component.css']
})
export class CareerComponent {
  selectedCarrier: CarrierDto = new CarrierDto;
  listCarrier: CarrierDto[] = [];

  constructor(private cs: CarrierService) { }

  ngOnInit(): void {
    this.getAllCarriers();
  }

  
  getAllCarriers() {
    this.cs.getAllCarriers().subscribe((response) => {
      this.listCarrier = response;
    });
  }

  add(carrier: CarrierDto) {
    this.cs.addCarrier(carrier).subscribe(
      (product) => {
        this.getAllCarriers();
        console.log(product);
      },
      (error) => {
        console.error('Failed to add carrier', error);
      }
    );
  }
  deleteCarrier(carrier: CarrierDto) {
    this.cs.deleteCarrier(carrier).subscribe(
      (response) => {
        this.getAllCarriers();
        console.log('Carrier deleted', response);
      },
      (error) => {
        console.error('Failed to delete carrier', error);
      }
    );
  }
  updateCarrier(carrier: CarrierDto) {
    this.cs.updateCarrier(carrier).subscribe(
      (response) => {
        this.getAllCarriers(); // Refresh the carrier list after updating
        console.log('Carrier updated', response);
      },
      (error) => {
        console.error('Failed to update carrier', error);
      }
    );
  }



}

