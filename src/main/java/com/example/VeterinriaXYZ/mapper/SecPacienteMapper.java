package com.example.VeterinriaXYZ.mapper;

import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.util.UtilDate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecPacienteMapper implements RowMapper<SecPaciente> {
    @Override
    public SecPaciente mapRow(ResultSet resultSet, int i) throws SQLException {
        SecPaciente entity = new SecPaciente();
        entity.setId(resultSet.getInt("id"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setEspecie(resultSet.getString("especie"));
        entity.setRaza(resultSet.getString("raza"));
        entity.setNacimiento(UtilDate.getLocalDate(resultSet.getDate("nacimiento")));
        entity.setTipoIdProp(resultSet.getString("tipoidprop"));
        entity.setIdProp(resultSet.getInt("idprop"));
        entity.setNombreProp(resultSet.getString("nombreprop"));
        entity.setCiudad(resultSet.getString("ciudad"));
        entity.setDireccion(resultSet.getString("direccion"));
        entity.setTelefono(resultSet.getLong("telefono"));
        entity.setFechaRegistro(UtilDate.getLocalDate(resultSet.getDate("fecharegistro")));
        return entity;
    }
}
