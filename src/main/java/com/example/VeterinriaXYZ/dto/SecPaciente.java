package com.example.VeterinriaXYZ.dto;

import com.example.VeterinriaXYZ.util.UtilDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
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
    private LocalDate nacimiento;

    private String tipoIdProp;
    private int idProp;
    private String nombreProp;
    private String ciudad;
    private String direccion;
    private Long telefono;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaRegistro;

    @JsonFormat
    public void setPacienteFromRs ( ResultSet rs) throws SQLException{
        id = rs.getInt("id");
        nombre = rs.getString("nombre");
        especie = rs.getString("especie");
        raza = rs.getString("raza");
        nacimiento = UtilDate.getLocalDate(rs.getDate("nacimiento"));
        tipoIdProp = rs.getString("tipoidprop");
        idProp = rs.getInt("idprop");
        nombreProp= rs.getString("nombreprop");
        ciudad= rs.getString("ciudad");
        direccion= rs.getString("direccion");
        telefono= rs.getLong("telefono");
        fechaRegistro = UtilDate.getLocalDate(rs.getDate("fechaRegistro"));
    }
}
