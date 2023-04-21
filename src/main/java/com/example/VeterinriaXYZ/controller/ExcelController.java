package com.example.VeterinriaXYZ.controller;

import com.example.VeterinriaXYZ.dao.SecPacienteDAO;
import com.example.VeterinriaXYZ.dto.SecPaciente;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Iterator;

@RestController("/api")
public class ExcelController {



    private final SecPacienteDAO secPacienteService;

    public ExcelController(SecPacienteDAO secPacienteService) {
        this.secPacienteService = secPacienteService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> importarExcel(@RequestParam("file") MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0); // asumiendo que el archivo de Excel tiene solo una hoja
            Iterator<Row> rows = sheet.iterator();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Iterator<Cell> cellsInRow = currentRow.iterator();

                SecPaciente paciente = new SecPaciente();
                int cellIndex = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndex) {
                        case 0: // primer celda del archivo Excel
                            paciente.setId((int) currentCell.getNumericCellValue());
                            break;
                        case 1: // segunda celda del archivo Excel
                            paciente.setNombre(currentCell.getStringCellValue());
                            break;
                        case 2: // tercera celda del archivo Excel
                            paciente.setEspecie(currentCell.getStringCellValue());
                            break;
                        case 3: // tercera celda del archivo Excel
                            paciente.setRaza(currentCell.getStringCellValue());
                            break;
                        case 4: // tercera celda del archivo Excel
                            paciente.setNacimiento(Date.valueOf(currentCell.getStringCellValue()));
                            break;
                        case 5: // tercera celda del archivo Excel
                            paciente.setIdPer(Integer.parseInt(currentCell.getStringCellValue()));
                            break;
                        case 6: // tercera celda del archivo Excel
                            paciente.setFechaRegistro(Date.valueOf(currentCell.getStringCellValue()));
                            break;
                        // otros casos para los demás atributos
                        default:
                            break;
                    }

                    cellIndex++;
                }

                secPacienteService.insert(paciente);
            }

            return ResponseEntity.ok("Archivo de Excel importado exitosamente.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar el archivo de Excel.");
        }
    }
}
