package com.example.VeterinriaXYZ.dao;

import com.example.VeterinriaXYZ.dto.SecPaciente;

import java.util.List;

public interface SecPacienteDAO {

    List<SecPaciente> getAll();

    SecPaciente getById(int nmid);

    SecPaciente insert(SecPaciente entity);

    SecPaciente update(SecPaciente entity);

    SecPaciente getByIdPersona(int nmid);

    void guardarPacientes(List<SecPaciente> pacientes);

    boolean existsById(int id);
}
