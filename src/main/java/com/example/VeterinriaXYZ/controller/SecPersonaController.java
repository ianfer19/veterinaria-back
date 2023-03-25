package com.example.VeterinriaXYZ.controller;

import com.example.VeterinriaXYZ.controller.errors.ApplicationCustomException;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.dto.SecPersona;
import com.example.VeterinriaXYZ.service.SecPersonaService;
import com.example.VeterinriaXYZ.util.MessagesConstants;
import com.example.VeterinriaXYZ.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SecPersonaController {
    private Logger log = LoggerFactory.getLogger(SecPacienteController.class);

    private static final String ENITY_NAME = "Sec_Persona";

    private final SecPersonaService secPersonaService;

    public SecPersonaController(SecPersonaService secPersonaService) {
        this.secPersonaService = secPersonaService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/persona")
    public ResponseEntity<ResponseMessage<SecPersona>> createSecPersona(@Valid @RequestBody SecPersona secPersona) throws ApplicationCustomException {
        log.debug("REST request to save sec_persona: {}", secPersona);
        SecPersona result = secPersonaService.save(secPersona);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/persona")
    public ResponseEntity<ResponseMessage<SecPersona>> updateSecPersona(@Valid @RequestBody SecPersona secPersona) throws ApplicationCustomException {
        log.debug("REST request to update sec_persona : {} ", secPersona);
        if(secPersona.getId() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        SecPersona result = secPersonaService.update(secPersona);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/persona")
    public ResponseEntity<ResponseMessage<List<SecPersona>>> getAllSecPersona(){
        log.debug("REST request to get all persona");
        return ResponseEntity.ok( new ResponseMessage<>(0,null, secPersonaService.findAll()));
    }
}
