package com.example.VeterinriaXYZ.dao;

import com.example.VeterinriaXYZ.dto.SecPersona;

import java.util.List;

public interface SecPersonaDAO {

    List<SecPersona> getAll();

    SecPersona getById(int nmid);

    SecPersona insert(SecPersona entity);
    SecPersona update(SecPersona entity);

}