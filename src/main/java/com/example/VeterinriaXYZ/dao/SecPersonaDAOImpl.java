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

    private static final String INSERT = "INSERT INTO sec_persona(identificacion, tipoid, nombre, ciudad," +
            "direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE sec_persona SET identificacion= ?,tipoid = ?, nombre= ?, ciudad= ?," +
            " direccion= ?, telefono= ? WHERE id= ?";

    private static final String SELECT ="SELECT id, identificacion ,tipoid, nombre, ciudad,direccion, telefono FROM sec_persona ";

    private static final String SELECTBYID = SELECT + " WHERE identificacion= ? ";

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
                entity.getIdentificacion(),
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
                entity.getIdentificacion(),
                entity.getTipoId(),
                entity.getNombre(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getId());
        return entity;
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM sec_persona WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count > 0;
    }
}