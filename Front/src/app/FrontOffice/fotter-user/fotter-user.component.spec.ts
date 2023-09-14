import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FotterUserComponent } from './fotter-user.component';

describe('FotterUserComponent', () => {
  let component: FotterUserComponent;
  let fixture: ComponentFixture<FotterUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FotterUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FotterUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
