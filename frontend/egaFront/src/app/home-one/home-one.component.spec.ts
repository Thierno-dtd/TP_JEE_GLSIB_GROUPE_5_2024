import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOneComponent } from './home-one.component';

describe('HomeOneComponent', () => {
  let component: HomeOneComponent;
  let fixture: ComponentFixture<HomeOneComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeOneComponent]
    });
    fixture = TestBed.createComponent(HomeOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
