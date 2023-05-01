package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dto.SecPaciente;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface SecPacienteService {

    SecPaciente save(SecPaciente application);

    SecPaciente update(SecPaciente application);

    List<SecPaciente> findAll();

    SecPaciente findOne(int id);

    boolean existePacientePorId(int id);

    List<SecPaciente> findPersona(int id);

    void guardarPacientes(List<SecPaciente> pacientes);

    ByteArrayInputStream exportAllData() throws IOException;

}
