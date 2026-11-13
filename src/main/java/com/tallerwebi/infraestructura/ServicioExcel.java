package com.tallerwebi.infraestructura;
import com.tallerwebi.dominio.Ejercicio;
import com.tallerwebi.dominio.RutinaDiaria;
import com.tallerwebi.dominio.RutinaSemanal;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public
class ServicioExcel {
    @Transactional
    public void getRutinaExcel(List<RutinaSemanal> rutinaSemanalList, ServletOutputStream nombreExcel) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rutina Semanal");

        // Estilo para el encabezado
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);

        int rowNum = 0;

        // Encabezados
        Row headerRow = sheet.createRow(rowNum++);
        Cell cell = headerRow.createCell(0);
        cell.setCellValue("Semana");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(1);
        cell.setCellValue("Duración");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(2);
        cell.setCellValue("Ejercicio");
        cell.setCellStyle(headerCellStyle);

        cell = headerRow.createCell(3);
        cell.setCellValue("Descripción");
        cell.setCellStyle(headerCellStyle);

        // Datos
        int semanaIndex = 1;
        for (RutinaSemanal rutinaSemanal : rutinaSemanalList) {
            int diaIndex = 1;
            for (RutinaDiaria rutinaDiaria : rutinaSemanal.getRutinaDiaria()) {
                Row diaRow = sheet.createRow(rowNum++);
                diaRow.createCell(0).setCellValue("Día " + diaIndex++);
                diaRow.createCell(1).setCellValue(rutinaSemanal.getTipoRutina());
                diaRow.createCell(2).setCellValue("");
                diaRow.createCell(3).setCellValue("");

                for (Ejercicio ejercicio : rutinaDiaria.getEjercicios()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue(ejercicio.getDuracion() + " min");
                    row.createCell(2).setCellValue(ejercicio.getNombre());
                    row.createCell(3).setCellValue(ejercicio.getDescripcion());
                }
            }
            semanaIndex++;
        }

        // Auto-ajustar columnas
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir el archivo Excel
        try {
            workbook.write(nombreExcel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
