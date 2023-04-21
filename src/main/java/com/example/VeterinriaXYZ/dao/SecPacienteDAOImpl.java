package com.example.VeterinriaXYZ.dao;

import com.example.VeterinriaXYZ.dto.SecPaciente;
import com.example.VeterinriaXYZ.mapper.SecPacienteMapper;
import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class SecPacienteDAOImpl implements SecPacienteDAO{

    private static final String INSERT = "INSERT INTO sec_paciente(nombre,especie,raza,nacimiento,idper,fecharegistro) VALUES (?, ?, ?, ?, ?, ?)";


    private static final String UPDATE = "UPDATE sec_paciente SET nombre= ?, especie= ?, raza= ?, nacimiento= ?, idper= ? , fecharegistro = ? WHERE id= ?";
    private static final String SELECT ="SELECT * FROM sec_paciente ";

    private static final String SELECTBYID = SELECT + " WHERE id= ? ";

    private static final String SELECTBYIDPERSONA = SELECT + " WHERE idper= ? ";

    private static final String EXIST ="SELECT COUNT(*) > 0 AS existe_paciente FROM sec_paciente WHERE id = ?;";


    private SessionFactory sessionFactory;
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
                entity.getIdPer(),
                entity.getFechaRegistro());
        return entity;
    }

    @Override
    public SecPaciente update(SecPaciente entity) {
        jdbcTemplate.update(UPDATE,
                entity.getNombre(),
                entity.getEspecie(),
                entity.getRaza(),
                entity.getNacimiento(),
                entity.getIdPer(),
                entity.getFechaRegistro(),
                entity.getId());
        return entity;
    }

    @Override
    public SecPaciente getByIdPersona(int nmid) {
        return jdbcTemplate.queryForObject(SELECTBYIDPERSONA, new SecPacienteMapper(), nmid);
    }

    public void guardarPacientes (List<SecPaciente> pacientes){
        jdbcTemplate.batchUpdate(INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                SecPaciente paciente = pacientes.get(i);
                ps.setString(1,paciente.getNombre());
                ps.setString(2,paciente.getEspecie());
                ps.setString(3,paciente.getRaza());
                ps.setDate(4,paciente.getNacimiento());
                ps.setInt(5,paciente.getIdPer());
                ps.setDate(6,paciente.getFechaRegistro());
            }
            @Override
            public int getBatchSize() {
                return pacientes.size();
            }
        });
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM sec_paciente WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count > 0;
    }
}
