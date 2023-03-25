package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dao.SecPersonaDAO;
import com.example.VeterinriaXYZ.dto.SecPersona;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SecPersonaServiceImpl implements SecPersonaService {

    private final SecPersonaDAO secPersonaDAO;

    private final Logger log = LoggerFactory.getLogger(SecPacienteServiceImpl.class);

    public SecPersonaServiceImpl(SecPersonaDAO secPersonaDAO) {
        this.secPersonaDAO = secPersonaDAO;
    }


    @Override
    public SecPersona save(SecPersona secPersona) {
        log.debug("Request to insert sec_persona: {}", secPersona);
        return secPersonaDAO.insert(secPersona);
    }

    @Override
    public SecPersona update(SecPersona secPersona) {
        log.debug("Request to get all sec_persona : {}", secPersona);
        return secPersonaDAO.update(secPersona);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<SecPersona> findAll() {
        log.debug("Request to get all sec_persona");
        return secPersonaDAO.getAll();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public SecPersona findOne(int id) {
        log.debug("Request to get sec_persona : {}", id);
        return secPersonaDAO.getById(id);
    }
}