package guru.qa.lesson10.utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    private final String zipPath;
    private final ClassLoader cl = ZipUtils.class.getClassLoader();

    public ZipUtils(String zipPath) {
        this.zipPath = zipPath;
    }

    public PDF getFirstPdf() throws Exception {
        try (InputStream is = cl.getResourceAsStream(zipPath);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    return new PDF(new ByteArrayInputStream(baos.toByteArray()));
                }
            }
        }
        return null;
    }

    public List<String[]> getFirstCvsAsTable() throws Exception {
        try (InputStream is = cl.getResourceAsStream(zipPath);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())))) {
                        return csvReader.readAll();
                    }
                }
            }
        }
        return null;
    }

    public XLS getFirstXlsx() throws Exception {
        try (InputStream is = cl.getResourceAsStream(zipPath);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    return new XLS(new ByteArrayInputStream(baos.toByteArray()));
                }
            }
        }
        return null;
    }
}
