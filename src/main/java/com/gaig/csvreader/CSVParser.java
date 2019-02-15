package com.gaig.csvreader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class CSVParser {

    public static void parsing(String fileName) {

        String name = "";
        try {
            String PATH_NAME = "C:\\Development\\UpdatedTextFile.txt";
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            PrintWriter writer = new PrintWriter(PATH_NAME, "UTF-8");
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        name = currentCell.getStringCellValue();
                        writer.println(name);
                    }
                }
            }
            writer.close();
            NotepadParser.notepadParse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
