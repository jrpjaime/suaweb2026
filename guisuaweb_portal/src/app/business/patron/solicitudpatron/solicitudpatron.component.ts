import { Component, OnInit, inject, signal, effect } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';

// Importaciones de Arquitectura Base
import { BaseComponent } from '../../../shared/base/base.component';
import { SharedService } from '../../../shared/services/shared.service';

// Servicios y DTOs de Catálogos
import { CatalogosService } from '../../catalogos/services/catalogos.service';
import { TipoCuotaDto } from '../../catalogos/model/TipoCuotaDto';
import { RegistroPatronalDto } from '../../catalogos/model/RegistroPatronalDto';

@Component({
  selector: 'app-solicitudpatron',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './solicitudpatron.component.html'
})
export class SolicitudpatronComponent extends BaseComponent implements OnInit {

  // 1. Inyección de dependencias mediante inject()
  private readonly fb = inject(FormBuilder);
  private readonly catalogosService = inject(CatalogosService);

  // 2. Definición de Signals para manejo de estado reactivo de catálogos
  registrosPatronales = signal<RegistroPatronalDto[]>([]);
  tiposCuota = signal<TipoCuotaDto[]>([]);

  // 3. Estructura del Formulario Reactivo con validaciones institucionales
  formSolicitud = this.fb.group({
    registroPatronal: [null, Validators.required],
    cveIdTipoCuota: [null, Validators.required],
    periodo: ['', [Validators.required, Validators.pattern('^[0-9]{6}$')]] // Formato YYYYMM
  });

  constructor(sharedService: SharedService) {
    super(sharedService);

    /**
     * EFFECT: Detecta cambios en el RFC de sesión heredado de BaseComponent.
     * Se dispara automáticamente cuando SharedService emite un nuevo valor.
     */
    effect(() => {
      const rfcActual = this.rfcSesion;
      console.log('[SolicitudPatron] Monitoreo de RFC en sesión:', rfcActual);

      if (rfcActual && rfcActual.trim() !== '') {
        this.cargarRegistrosPatronales(rfcActual);
      }
    });
  }

  /**
   * Inicialización del componente.
   * Es vital llamar a recargaParametros() para activar las suscripciones del BaseComponent.
   */
  override ngOnInit(): void {
    console.log('[SolicitudPatron] ngOnInit invocado');
    this.recargaParametros(); // Carga RFC, Nombre, Roles, etc. desde SharedService
    super.ngOnInit();

    // El catálogo de tipos de cuota es independiente del usuario, se carga de inmediato
    this.cargarTiposCuota();
  }

  /**
   * Obtiene la lista de tipos de cuota (IMSS/RCV) desde el microservicio.
   */
  private cargarTiposCuota(): void {
    console.log('[SolicitudPatron] Consultando catálogo de tipos de cuota...');
    this.catalogosService.getTiposCuota().subscribe({
      next: (data) => {
        console.log('[SolicitudPatron] Tipos de cuota recibidos:', data);
        this.tiposCuota.set(data);
      },
      error: (err) => console.error('[SolicitudPatron] Error al cargar tipos de cuota:', err)
    });
  }

  /**
   * Obtiene los registros patronales asociados al RFC del usuario autenticado.
   * @param rfc RFC extraído de la sesión.
   */
  private cargarRegistrosPatronales(rfc: string): void {
    console.log('[SolicitudPatron] Consultando registros patronales para:', rfc);

    this.catalogosService.consultarRegistrosPatronales({ rfc }).subscribe({
      next: (data) => {
        console.log('[SolicitudPatron] Registros patronales encontrados:', data.length);
        this.registrosPatronales.set(data);
      },
      error: (err) => {
        console.error('[SolicitudPatron] Error en la consulta de registros patronales:', err);
        this.registrosPatronales.set([]); // Limpiar en caso de error
      }
    });
  }

  /**
   * Lógica disparada al presionar el botón "Continuar".
   */
  enviarConsulta(): void {
    if (this.formSolicitud.valid) {
      console.log('Formulario válido. Enviando datos:', this.formSolicitud.value);
    } else {
      console.warn('El formulario contiene errores.');
      this.formSolicitud.markAllAsTouched();
    }
  }
}
