import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.log4j.Logger;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class for create report
 */
public class Report {

    private static final Logger log_PDF = Logger.getLogger(Report.class);
    private static final Logger log_HTML = Logger.getLogger(Report.class);

    /**
     * method for create pdf report
     * @param model model of table
     * @throws FileNotFoundException if file not found
     */
    public void createPDF(DefaultTableModel model) throws FileNotFoundException {
        log_PDF.info("PDF create start");
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./Report.pdf"));
        pdfDoc.addNewPage();
        Document doc = new Document(pdfDoc);
        Paragraph paragraph = new Paragraph("Report");
        doc.add(paragraph);
        String column[] = {"Country", "Manufacturer", "Calibr", "Type Gun", "Count ammo","Weight", "Cost", "Aviabillity"};
        Table table = new Table(column.length);

        for(String s: column) {
            table.addCell(new Cell().add(s));
        }

        for(int i = 0;i < model.getRowCount();i++) {
            for(int j = 0;j < model.getColumnCount();j++) {
                table.addCell(new Cell().add(model.getValueAt(i,j).toString()));
            }
        }

        doc.add(table);
        doc.close();
        log_PDF.info("PDF create finish");
    }

    /**
     * method for create html report
     * @param model model of table
     */
    public void createHTML(DefaultTableModel model) {
        log_HTML.info("HTML create start");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("./Report.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>Country<TH>Manufacturer<TH>Calibr<TH>Type Gun<TH>Count ammo<TH>Weight<TH>Cost<TH>Aviabillity</TR>");
        for(int i = 0; i < model.getRowCount(); i++) {
            int square = i * i;
            pw.println("<TR><TD>" + (String) model.getValueAt(i,0)
                    + "<TD>" + (String) model.getValueAt(i,1)
                    + "<TD>" + (String) model.getValueAt(i,2)
                    + "<TD>" + (String) model.getValueAt(i,3)
                    + "<TD>" + (String) model.getValueAt(i,4)
                    + "<TD>" + (String) model.getValueAt(i,5)
                    + "<TD>" + (String) model.getValueAt(i,6)
                    + "<TD>" + (String) model.getValueAt(i,7));
        }
        pw.println("</TABLE>");
        pw.close();
        log_HTML.info("HTML create finish");
    }
}
