package mx.gob.imss.seguridad.service;

import java.util.Optional;

import mx.gob.imss.seguridad.dto.UsuarioDto;

public interface UsuarioService { 
     public Optional<UsuarioDto> getUsuarioInfoByRfc(String rfc);
} 