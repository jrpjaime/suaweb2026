package mx.gob.imss.catalogos.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.imss.catalogos.dto.RegistroPatronalDto;
import mx.gob.imss.catalogos.dto.RegistroPatronalRequestDto;
import mx.gob.imss.catalogos.repository.SwtPatronRepository; 
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final SwtPatronRepository patronRepository;

    @Override
    public List<RegistroPatronalDto> obtenerRegistrosPatronalesPorRfc(RegistroPatronalRequestDto request) {
        log.info("Iniciando consulta de registros patronales para el RFC: {}", request.getRfc());

        // Consultar la base de datos a través del repositorio
        return patronRepository.findByRefRfcAndFecRegistroBajaIsNull(request.getRfc())
                .stream()
                .map(entity -> RegistroPatronalDto.builder()
                        .registroPatronal(entity.getCveRegistroPatronal())
                        .razonSocial(entity.getNomRazonSocial())
                        .rfc(entity.getRefRfc())
                        .domicilio(entity.getRefDomicilio())
                        .build())
                .collect(Collectors.toList());
    }
}