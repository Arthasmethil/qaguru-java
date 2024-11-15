package guru.qa.lesson10;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import guru.qa.lesson10.utils.ZipUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipReadingTests {

    ZipUtils zipUtils = new ZipUtils("guru/qa/lesson10/archiveFiles.zip");

    @Test
    void pdfFileParsingTest() throws Exception {
        PDF pdfFile = zipUtils.getFirstPdf();

        assertThat(pdfFile.numberOfPages).isGreaterThan(0);
        assertThat(pdfFile.text).containsIgnoringWhitespaces("This is a test PDF document.\n" +
                "If you can read this, you have Adobe Acrobat Reader installed on your computer.");
        assertThat(pdfFile.author).contains("Gene Brumblay");
    }

    @Test
    void xlsFileParsingTest() throws Exception {
        XLS xls = zipUtils.getFirstXlsx();
        Sheet sheet = xls.excel.getSheetAt(0);

        assertThat(xls.excel.getNumberOfSheets()).isGreaterThan(0);
        assertThat(sheet.getPhysicalNumberOfRows()).isGreaterThan(0);
        assertThat(sheet.getRow(0).getCell(0).getStringCellValue()).isEqualTo("android device");
        assertThat(sheet.getRow(1).getCell(1).getNumericCellValue()).isEqualTo(80);
        assertThat(sheet.getRow(2).getCell(2).getNumericCellValue()).isEqualTo(4);
        assertThat(sheet.getRow(4).getCell(0).getStringCellValue()).isEqualTo("Realme C11");
    }

    @Test
    void csvFileParsingTest() throws Exception {
        List<String[]> cvsTable = zipUtils.getFirstCvsAsTable();
        assertThat(cvsTable).contains(new String[]{"Service", "Price per minutes", "Price per month", "Thread limit"});
        assertThat(cvsTable).contains(new String[]{"Browserstack", "100", "1000", "unlim"});
        assertThat(cvsTable.size()).isEqualTo(4);

    }
}
