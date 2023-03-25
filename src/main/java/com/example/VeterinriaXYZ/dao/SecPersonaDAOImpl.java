package com.example.VeterinriaXYZ.dao;

import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.dto.SecPersona;
import com.example.VeterinriaXYZ.mapper.SecPacienteMapper;
import com.example.VeterinriaXYZ.mapper.SecPersonaMapper;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@Transactional
public class SecPersonaDAOImpl implements SecPersonaDAO{

    private static final String INSERT = "INSERT INTO sec_persona(id, tipoid, nombre, ciudad," +
            "direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE sec_persona SET id= ?, tipoid = ?, nombre= ?, ciudad= ?," +
            " direccion= ?, telefono= ? WHERE id= ?";

    private static final String SELECT ="SELECT id, tipoid, nombre, ciudad,direccion, telefono FROM sec_persona ";

    private static final String SELECTBYID = SELECT + " WHERE id= ? ";

    JdbcTemplate jdbcTemplate;

    public SecPersonaDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<SecPersona> getAll() {
        return jdbcTemplate.query(SELECT, new SecPersonaMapper());
    }

    @Override
    public SecPersona getById(int id) {
        return jdbcTemplate.queryForObject(SELECTBYID, new SecPersonaMapper(), id);
    }

    @Override
    public SecPersona insert(SecPersona entity) {
        jdbcTemplate.update(INSERT,
                entity.getId(),
                entity.getTipoId(),
                entity.getNombre(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono());
        return entity;
    }

    @Override
    public SecPersona update(SecPersona entity) {
        jdbcTemplate.update(UPDATE,
                entity.getId(),
                entity.getTipoId(),
                entity.getNombre(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getId());
        return entity;
    }
}