import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddUserDetailComponent } from './add-user-detail.component';

describe('AddUserDetailComponent', () => {
  let component: AddUserDetailComponent;
  let fixture: ComponentFixture<AddUserDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddUserDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddUserDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
