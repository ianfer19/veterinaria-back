package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dao.SecPacienteDAO;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SecPacienteServiceImpl implements SecPacienteService {

    private final SecPacienteDAO secPacienteDAO;

    private  final Logger log = LoggerFactory.getLogger(SecPacienteServiceImpl.class);

    public SecPacienteServiceImpl(SecPacienteDAO secPacienteDAO) {
        this.secPacienteDAO = secPacienteDAO;
    }

    @Override
    public SecPaciente save(SecPaciente secPaciente){
        log.debug("Request to insert sec_paciente: {}", secPaciente);
        return secPacienteDAO.insert(secPaciente);
    }

    @Override
    public SecPaciente update(SecPaciente secPaciente){
        log.debug("Request to get all sec_paciente : {}", secPaciente);
        return secPacienteDAO.update(secPaciente);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<SecPaciente> findAll() {
        log.debug("Request to get all sec_paciente");
        return secPacienteDAO.getAll();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public SecPaciente findOne(int id) {
        log.debug("Request to get sec_paciente : {}", id);
        return secPacienteDAO.getById(id);
    }

    @Override
    public boolean existePacientePorId(int id) {
        return secPacienteDAO.existsById(id);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<SecPaciente> findPersona(int id) {
        log.debug("Request to get sec_paciente : {}", id);
        return (List<SecPaciente>) secPacienteDAO.getByIdPersona(id);
    }

    @Override
    public void guardarPacientes(List<SecPaciente> pacientes) {
        secPacienteDAO.guardarPacientes(pacientes);
    }

    @Override
    public ByteArrayInputStream exportAllData() throws IOException {
        String [] columns= {"Id", "Nombre", "Especie","Raza","Nacimiento","IdPer","FechaRegistro"};

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Pacientes");
        Row row = sheet.createRow(0);

        for(int i=0; i<columns.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }
        List<SecPaciente> pacientes = this.findAll();
        int initRow = 1;
        for(SecPaciente paciente:pacientes){
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(paciente.getId());
            row.createCell(1).setCellValue(paciente.getNombre());
            row.createCell(2).setCellValue(paciente.getEspecie());
            row.createCell(3).setCellValue(paciente.getRaza());
            row.createCell(4).setCellValue(paciente.getNacimiento().toString());
            row.createCell(5).setCellValue(paciente.getIdPer());
            row.createCell(6).setCellValue(paciente.getFechaRegistro().toString());

            initRow++;
        }
        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
    public void guardarPacientes(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<SecPaciente> pacientes = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
                secPacienteDAO.guardarPacientes(pacientes);
            } catch (IOException e) {
                throw new IllegalArgumentException("Archivo no valido");
            }
        }
    }

}
