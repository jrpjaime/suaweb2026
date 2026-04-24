import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudregistroComponent } from './solicitudregistro.component';

describe('SolicitudregistroComponent', () => {
  let component: SolicitudregistroComponent;
  let fixture: ComponentFixture<SolicitudregistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudregistroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudregistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
