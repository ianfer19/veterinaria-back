package com.example.VeterinriaXYZ.service;

import com.example.VeterinriaXYZ.dto.SecPaciente;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {


    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
   public static List<SecPaciente> getCustomersDataFromExcel(InputStream inputStream){
        List<SecPaciente> customers = new ArrayList<>();
       try {
           XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
           XSSFSheet sheet = workbook.getSheet("pacientes");
           int rowIndex =0;
           for (Row row : sheet){
               if (rowIndex ==0){
                   rowIndex++;
                   continue;
               }
               Iterator<Cell> cellIterator = row.iterator();
               int cellIndex = 0;
               SecPaciente paciente = new SecPaciente();
               while (cellIterator.hasNext()){
                   Cell cell = cellIterator.next();
                   switch (cellIndex){
                       case 0:
                           paciente.setId((int) cell.getNumericCellValue());
                           break;
                       case 1:
                           paciente.setNombre(cell.getStringCellValue());
                           break;
                       case 2:
                           paciente.setEspecie(cell.getStringCellValue());
                           break;
                       case 3:
                           paciente.setRaza(cell.getStringCellValue());
                           break;
                       case 4:
                           paciente.setNacimiento(Date.valueOf(cell.getStringCellValue()));
                           break;
                       case 5:
                           paciente.setIdPer((int) cell.getNumericCellValue());
                           break;
                       case 6:
                           paciente.setFechaRegistro(Date.valueOf(cell.getStringCellValue()));
                           break;
                       default:
                           break;
                   }
                   cellIndex++;
               }

               customers.add(paciente);
           }
       } catch (IOException e) {
           e.getStackTrace();
       }
       return customers;
   }
}