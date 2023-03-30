package com.example.VeterinriaXYZ.controller;

import com.example.VeterinriaXYZ.controller.errors.ApplicationCustomException;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.service.SecPacienteService;
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
public class SecPacienteController {

    private Logger log = LoggerFactory.getLogger(SecPacienteController.class);

    private static final String ENITY_NAME = "Sec_Paciente";

    private final SecPacienteService secPacienteService;


    public SecPacienteController(SecPacienteService secPacienteService) {
        this.secPacienteService = secPacienteService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/paciente")
    public ResponseEntity<ResponseMessage<SecPaciente>> createSecPaciente(@Valid @RequestBody SecPaciente secPaciente) throws ApplicationCustomException {
        log.debug("REST request to save sec_paciente: {}", secPaciente);
        SecPaciente result = secPacienteService.save(secPaciente);
        return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/paciente")
    public ResponseEntity<ResponseMessage<SecPaciente>> updateSecPaciente(@Valid @RequestBody SecPaciente secPaciente) throws ApplicationCustomException {
        log.debug("REST request to update sec_paciente : {} ", secPaciente);
        if(secPaciente.getId() == 0){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE,String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }
        SecPaciente result = secPacienteService.update(secPaciente);
        return ResponseEntity.ok( new ResponseMessage<>(0, null, result));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paciente")
    public ResponseEntity<ResponseMessage<List<SecPaciente>>> getAllSecPaciente(){
        log.debug("REST request to get all paciente");
        return ResponseEntity.ok( new ResponseMessage<>(0,null, secPacienteService.findAll()));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paciente/{id}")
    public ResponseEntity<ResponseMessage<SecPaciente>> getSec_usuario(@PathVariable int id) throws ApplicationCustomException {
        log.debug("REST request to get sec_paciente : {}", id);
        SecPaciente secPaciente= secPacienteService.findOne(id);
        if(secPaciente == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }else {
            return ResponseEntity.ok(new ResponseMessage<>(0,null,secPaciente));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paciente-persona/{id}")
    public ResponseEntity<ResponseMessage<SecPaciente>> getSec_usuarioPersona(@PathVariable int id) throws ApplicationCustomException {
        log.debug("REST request to get sec_paciente : {}", id);
        SecPaciente secPaciente= secPacienteService.findPersona(id);
        if(secPaciente == null){
            throw new ApplicationCustomException(MessagesConstants.ENTITY_ALREADY_EXISTS_CODE, String.format(MessagesConstants.ENTITY_NOT_EXISTS, ENITY_NAME));
        }else {
            return ResponseEntity.ok(new ResponseMessage<>(0,null,secPaciente));
        }
    }
}
