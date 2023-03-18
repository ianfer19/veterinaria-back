package com.example.VeterinriaXYZ.dao;

import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.mapper.SecPacienteMapper;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@Transactional
public class SecPacienteDAOImpl implements SecPacienteDAO{

    private static final String INSERT = "INSERT INTO sec_paciente(nombre,especie , raza, nacimiento, tipoidprop, idprop, nombreprop, ciudad," +
            "direccion, telefono, fecharegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

    private static final String UPDATE = "UPDATE sec_paciente SET nombre= ?, especie= ?, raza= ?, nacimiento= ?, tipoidprop = ?, idprop= ?, nombreprop= ?, ciudad= ?," +
            " direccion= ?, telefono= ?, fecharegistro= ? WHERE id= ?";

    private static final String SELECT ="SELECT id ,nombre,especie , raza, nacimiento,tipoidprop, idprop, nombreprop, ciudad,direccion, telefono, fecharegistro FROM sec_paciente ";

    private static final String SELECTBYID = SELECT + " WHERE id= ? ";

    JdbcTemplate jdbcTemplate;

    public SecPacienteDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<SecPaciente> getAll() {
        return jdbcTemplate.query(SELECT, new SecPacienteMapper());
    }

    @Override
    public SecPaciente getById(int nmid) {
        return jdbcTemplate.queryForObject(SELECTBYID, new SecPacienteMapper(), nmid);
    }

    @Override
    public SecPaciente insert(SecPaciente entity) {
        jdbcTemplate.update(INSERT,
                entity.getNombre(),
                entity.getEspecie(),
                entity.getRaza(),
                entity.getNacimiento(),
                entity.getTipoIdProp(),
                entity.getIdProp(),
                entity.getNombreProp(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getFechaRegistro());
        return entity;
    }

    @Override
    public SecPaciente update(SecPaciente entity) {
        jdbcTemplate.update(UPDATE,
                entity.getId(),
                entity.getNombre(),
                entity.getEspecie(),
                entity.getRaza(),
                entity.getNacimiento(),
                entity.getTipoIdProp(),
                entity.getIdProp(),
                entity.getNombreProp(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getFechaRegistro());
        return entity;
    }
}
