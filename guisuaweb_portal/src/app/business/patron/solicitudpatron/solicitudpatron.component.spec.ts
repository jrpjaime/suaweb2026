import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudpatronComponent } from './solicitudpatron.component';

describe('SolicitudpatronComponent', () => {
  let component: SolicitudpatronComponent;
  let fixture: ComponentFixture<SolicitudpatronComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudpatronComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudpatronComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
