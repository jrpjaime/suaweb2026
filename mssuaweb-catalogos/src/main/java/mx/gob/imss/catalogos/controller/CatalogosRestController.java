package mx.gob.imss.catalogos.controller;

 
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.imss.catalogos.dto.RegistroPatronalDto;
import mx.gob.imss.catalogos.dto.RegistroPatronalRequestDto;
import mx.gob.imss.catalogos.dto.TipoCuotaDto;
import mx.gob.imss.catalogos.service.PatronService;
import mx.gob.imss.catalogos.service.SatService;
import mx.gob.imss.catalogos.service.TipoCuotaService; 
 


@RestController
@CrossOrigin("*") 
@RequestMapping("/mssuaweb-catalogos/v1")
public class CatalogosRestController {
	private final static Logger logger = LoggerFactory.getLogger(CatalogosRestController.class);
  


	@Autowired
    private TipoCuotaService tipoCuotaService;

	@Autowired 
    private SatService satService;


 	@Autowired
    private PatronService patronService;  
 
    @GetMapping("/info")
	public ResponseEntity<List<String>> info() {
		logger.info("........................mssuaweb-catalogos info..............................");
		List<String> list = new ArrayList<String>();
		list.add("mssuaweb-catalogos");
		list.add("20240927");
		list.add("Catálogos");
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}


	@GetMapping("/list")
	public ResponseEntity<List<String>> list() {
		logger.info("........................mssuaweb-catalogos list..............................");
		List<String> list = new ArrayList<String>();
		list.add("mssuaweb-catalogos");
		list.add("20240927");
		list.add("Catálogos");
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}


 
 


    /**
     * Endpoint para consultar registros patronales vinculados a un RFC.
     * Tipo: POST para evitar parámetros expuestos en URL y manejar datos en el body.
     */
    @PostMapping("/consultarRegistrosPatronales")
    public ResponseEntity<List<RegistroPatronalDto>> consultarRegistrosPatronales(
            @RequestBody RegistroPatronalRequestDto request) {
        
        logger.info("Petición POST recibida en /consultarRegistrosPatronales");
        
        if (request.getRfc() == null || request.getRfc().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<RegistroPatronalDto> respuesta = patronService.obtenerRegistrosPatronalesPorRfc(request);
        
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

 



    /**
     * Endpoint para obtener el catálogo de Tipos de Cuota.
     * URL: /mssuaweb-seguridad/v1/tiposCuota
     * @return ResponseEntity con lista de TipoCuotaDto.
     */
    @GetMapping("/tiposCuota")
    public ResponseEntity<List<TipoCuotaDto>> tiposCuota() {
        logger.info("Solicitud recibida en el servicio tipoCuotaService para listar tipos de cuota.");
        
        try {
            // Llamada al servicio especializado
            List<TipoCuotaDto> respuesta = tipoCuotaService.getTiposCuota();
            
            logger.info("Respuesta generada exitosamente con {} registros.", respuesta.size());
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Error al obtener los tipos de cuota: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}