import { TestBed } from '@angular/core/testing';

import { SolicitudpatronService } from './solicitudpatron.service';

describe('SolicitudpatronService', () => {
  let service: SolicitudpatronService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudpatronService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
