package com.example.VeterinriaXYZ.dto;

import com.example.VeterinriaXYZ.util.UtilDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecPaciente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String especie;
    private String raza;


    @JsonFormat(pattern="yyyy-MM-dd")
    private Date nacimiento;

    private int idPer;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaRegistro;

    @JsonFormat
    public void setPacienteFromRs ( ResultSet rs) throws SQLException{
        id = rs.getInt("id");
        nombre = rs.getString("nombre");
        especie = rs.getString("especie");
        raza = rs.getString("raza");
        nacimiento = Date.valueOf(UtilDate.getLocalDate(rs.getDate("nacimiento")));
        idPer = rs.getInt("idper");
        fechaRegistro = Date.valueOf(UtilDate.getLocalDate(rs.getDate("fechaRegistro")));
    }
}
