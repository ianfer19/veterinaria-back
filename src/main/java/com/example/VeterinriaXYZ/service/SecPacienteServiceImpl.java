package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dao.SecPacienteDAO;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;

@Service
@Transactional
public class SecPacienteServiceImpl implements SecPacienteService {

    private final SecPacienteDAO secPacienteDAO;

    private  final Logger log = LoggerFactory.getLogger(SecPacienteServiceImpl.class);

    public SecPacienteServiceImpl(SecPacienteDAO secPacienteDAO) {
        this.secPacienteDAO = secPacienteDAO;
    }

    @Override
    public SecPaciente save(SecPaciente secPaciente){
        log.debug("Request to insert sec_paciente: {}", secPaciente);
        return secPacienteDAO.insert(secPaciente);
    }

    @Override
    public SecPaciente update(SecPaciente secPaciente){
        log.debug("Request to get all sec_paciente : {}", secPaciente);
        return secPacienteDAO.update(secPaciente);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<SecPaciente> findAll() {
        log.debug("Request to get all sec_paciente");
        return secPacienteDAO.getAll();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public SecPaciente findOne(int id) {
        log.debug("Request to get sec_paciente : {}", id);
        return secPacienteDAO.getById(id);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public SecPaciente findPersona(int id) {
        log.debug("Request to get sec_paciente : {}", id);
        return secPacienteDAO.getByIdPersona(id);
    }
}
