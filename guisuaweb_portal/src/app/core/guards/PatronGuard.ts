import { AuthService } from '../services/auth.service';
import { SharedService } from '../../shared/services/shared.service';
import { Constants } from '../../global/Constants';
import { map, take } from 'rxjs/operators';
import { AlertService } from '../../shared/services/alert.service';
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

/**
 * Guard para proteger las rutas de la sección Patrón.
 * Valida que el usuario tenga el rol de Patrón o Representante Legal.
 */
export const PatronGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const sharedService = inject(SharedService);
  const router = inject(Router);
  const alertService = inject(AlertService);

  // 1. Verificación básica de autenticación
  if (!authService.isAuthenticated()) {
    router.navigate(['/login']);
    return false;
  }

  // 2. Validación de roles mediante el SharedService (Programación Reactiva)
  return sharedService.currentRoleSesion.pipe(
    take(1), // Tomamos solo la última emisión de roles para la validación del Guard
    map(roles => {

      // Verificamos si el usuario tiene rol de Patrón o de Representante
      const esPatron = roles?.includes(Constants.rolePatron);
      const esRepresentante = roles?.includes(Constants.roleRepresentante);

      if (esPatron || esRepresentante) {
        return true; // Acceso permitido
      } else {
        // Acceso denegado: Notificamos al usuario y redirigimos al Home
        alertService.error('No tiene los permisos necesarios para acceder a las funciones patronales.');
        router.navigate(['/home']);
        return false;
      }
    })
  );
};
