package com.example.VeterinriaXYZ.mapper;

import com.example.VeterinriaXYZ.dto.SecPersona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecPersonaMapper implements RowMapper<SecPersona> {

    @Override
    public SecPersona mapRow(ResultSet resultSet, int i) throws SQLException {
        SecPersona entity = new SecPersona();
        entity.setId(resultSet.getInt("id"));
        entity.setTipoId(resultSet.getString("tipoid"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setCiudad(resultSet.getString("ciudad"));
        entity.setDireccion(resultSet.getString("direccion"));
        entity.setTelefono(resultSet.getLong("telefono"));
        return entity;
    }
}