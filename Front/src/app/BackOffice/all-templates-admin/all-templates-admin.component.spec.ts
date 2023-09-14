import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTemplatesAdminComponent } from './all-templates-admin.component';

describe('AllTemplatesAdminComponent', () => {
  let component: AllTemplatesAdminComponent;
  let fixture: ComponentFixture<AllTemplatesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllTemplatesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllTemplatesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
