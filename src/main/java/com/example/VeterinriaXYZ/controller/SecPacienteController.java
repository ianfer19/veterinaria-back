package com.example.VeterinriaXYZ.controller;

import com.example.VeterinriaXYZ.controller.errors.ApplicationCustomException;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.service.SecPacienteService;
import com.example.VeterinriaXYZ.service.SecPacienteServiceImpl;
import com.example.VeterinriaXYZ.service.SecPersonaService;
import com.example.VeterinriaXYZ.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecPacienteController {

    private Logger log = LoggerFactory.getLogger(SecPacienteController.class);

    private static final String ENITY_NAME = "Sec_Paciente";

    private final SecPacienteService secPacienteService;
    private final SecPacienteServiceImpl secPacienteServiceImpl;

    private final SecPersonaService secPersonaService;

    public SecPacienteController(SecPacienteService secPacienteService, SecPacienteServiceImpl secPacienteServiceImpl, SecPersonaService secPersonaService) {
        this.secPacienteService = secPacienteService;
        this.secPacienteServiceImpl = secPacienteServiceImpl;
        this.secPersonaService = secPersonaService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/paciente")
    public ResponseEntity<ResponseMessage<SecPaciente>> createSecPaciente(@Valid @RequestBody SecPaciente secPaciente) throws ApplicationCustomException {
        log.debug("REST request to save sec_paciente: {}", secPaciente);
        boolean existeUsuario = secPacienteService.existePacientePorId(secPaciente.getId());
        if(existeUsuario==true){
            SecPaciente result= secPacienteService.update(secPaciente);
            return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
        }else {
            SecPaciente result= secPacienteService.save(secPaciente);
            return ResponseEntity.ok( new ResponseMessage<>(0,null, result));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/paciente")
    public ResponseEntity<ResponseMessage<SecPaciente>> updateSecPaciente(@Valid @RequestBody SecPaciente secPaciente) throws ApplicationCustomException {
        log.debug("REST request to update sec_paciente : {} ", secPaciente);
        boolean existeUsuario = secPacienteService.existePacientePorId(secPaciente.getId());
        if(existeUsuario==false) {
            return ResponseEntity.ok(new ResponseMessage<>(0, null, null));
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
        boolean existeUsuario = secPacienteService.existePacientePorId(id);
        if(existeUsuario==false){
            return ResponseEntity.ok(new ResponseMessage<>(0,null,null));
        }else {
            SecPaciente secPaciente= secPacienteService.findOne(id);
            return ResponseEntity.ok(new ResponseMessage<>(0,null,secPaciente));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paciente-exist/{id}")
    public boolean getSec_paciente_exist(@PathVariable int id) throws ApplicationCustomException {
        log.debug("REST request to get sec_paciente : {}", id);
        boolean existeUsuario = secPacienteService.existePacientePorId(id);
        if(existeUsuario==false){
            return false;
        }else {
            return true;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paciente-persona/{id}")
    public ResponseEntity<ResponseMessage<List<SecPaciente>>> getSec_usuarioPersona(@PathVariable int id) throws ApplicationCustomException {
        log.debug("REST request to get sec_paciente : {}", id);
            return ResponseEntity.ok(new ResponseMessage<>(0,null,secPacienteService.findPersona(id)));
    }

    @GetMapping("/export/all")
    public ResponseEntity<InputStreamResource> exportAllData() throws IOException {
        ByteArrayInputStream stream = secPacienteService.exportAllData();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition","attachment; filename=pacientes.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }

    @PostMapping("/import/all")
    public ResponseEntity<?> uploadPacientesData(@RequestParam("file") MultipartFile file){
        secPacienteServiceImpl.guardarPacientes(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Datos cargados correctamente"));
    }

}