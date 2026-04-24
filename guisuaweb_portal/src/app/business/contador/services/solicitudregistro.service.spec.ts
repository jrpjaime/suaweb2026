import { TestBed } from '@angular/core/testing';

import { SolicitudregistroService } from './solicitudregistro.service';

describe('SolicitudregistroService', () => {
  let service: SolicitudregistroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudregistroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
