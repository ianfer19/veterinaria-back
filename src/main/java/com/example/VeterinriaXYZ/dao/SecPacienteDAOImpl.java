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

    private static final String INSERT = "INSERT INTO sec_paciente(nombre,especie,raza,idper,nacimiento,fecharegistro) VALUES (?, ?, ?, ?, ?, ?)";


    private static final String UPDATE = "UPDATE sec_paciente SET nombre= ?, especie= ?, raza= ?, idper= ? ,nacimiento= ?, fecharegistro = ? WHERE id= ?";
    private static final String SELECT ="SELECT * FROM sec_paciente ";

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
                entity.getIdPer(),
                entity.getNacimiento(),
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
                entity.getIdPer(),
                entity.getNacimiento(),
                entity.getFechaRegistro());
        return entity;
    }
}
