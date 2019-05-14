package Excel;

import Entity.Resume;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelWriter {


    public boolean writeDataToExcel(ArrayList<Resume> dataToExport) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("Vacancies");

        ArrayList<Row> rows = new ArrayList<Row>();
        try {
            for (int i = 0; i < dataToExport.size(); i++) {
                Row row = sheet1.createRow(i);
                for (int j = 0; j < dataToExport.get(i).SUM_OF_CELLS; j++) {
                    Resume exportingResume = dataToExport.get(i);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(exportingResume.resumeToArray().get(j));
                }
                rows.add(row);
            }
            FileOutputStream fos = new FileOutputStream("C:\\Vacancies.xls");
            wb.write(fos);
            fos.close();
            System.out.println("Данные выгружены в EXCEL");
            return true;
        } catch (Exception e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            return false;
        }
    }

}
