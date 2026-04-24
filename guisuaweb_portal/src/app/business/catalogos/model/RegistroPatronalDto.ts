/**
 * DTO que representa un Registro Patronal vinculado a un RFC.
 * Utilizado para el catálogo con filtro de búsqueda en la Solicitud Patronal.
 */
export interface RegistroPatronalDto {
  // Identificador único del registro patronal (ej. A1234567810)
  registroPatronal: string;

  // Nombre o Razón Social del patrón
  razonSocial: string;

  // RFC vinculado al registro
  rfc?: string;

  // Opcionales: podrías incluir otros datos si el backend los devuelve
  cveIdDelegacion?: number;
  cveIdSubdelegacion?: number;
}
