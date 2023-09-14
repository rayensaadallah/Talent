import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FotterAdminComponent } from './fotter-admin.component';

describe('FotterAdminComponent', () => {
  let component: FotterAdminComponent;
  let fixture: ComponentFixture<FotterAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FotterAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FotterAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
