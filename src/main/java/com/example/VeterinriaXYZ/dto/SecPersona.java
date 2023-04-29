package com.example.VeterinriaXYZ.dto;

import com.example.VeterinriaXYZ.util.UtilDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecPersona implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int identificacion;
    private String tipoId;
    private String nombre;
    private String ciudad;
    private String direccion;
    private Long telefono;

    @JsonFormat
    public void setPacienteFromRs ( ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        identificacion = rs.getInt("identificacion");
        tipoId = rs.getString("tipoid");
        nombre= rs.getString("nombre");
        ciudad= rs.getString("ciudad");
        direccion= rs.getString("direccion");
        telefono= rs.getLong("telefono");
    }
}
