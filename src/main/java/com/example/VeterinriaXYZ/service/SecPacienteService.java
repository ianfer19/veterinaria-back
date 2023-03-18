package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dto.SecPaciente;

import java.util.List;

public interface SecPacienteService {

    SecPaciente save(SecPaciente application);

    SecPaciente update(SecPaciente application);

    List<SecPaciente> findAll();

    SecPaciente findOne(int id);
}
