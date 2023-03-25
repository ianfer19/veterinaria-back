package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dto.SecPersona;

import java.util.List;

public interface SecPersonaService {

    SecPersona save(SecPersona application);

    SecPersona update(SecPersona application);

    List<SecPersona> findAll();

    SecPersona findOne(int id);
}