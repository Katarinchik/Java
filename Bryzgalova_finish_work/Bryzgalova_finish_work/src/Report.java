import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.itextpdf.kernel.pdf.PdfName.BaseFont;
import static com.itextpdf.kernel.pdf.PdfName.FontFamily;

/**
 * class for create report
 */
public class Report {

    private String FONT = "./arial.ttf";
    /**
     * method for create report in PDF
     * @param model object type of DefaultTableModel
     * @throws FileNotFoundException exception throws if file not found
     */
    void createPDF(DefaultTableModel model, String [] column, int flag) throws IOException {
        String text = new String();
        if (flag == 1) {
            text = "ReportInfo";
        }
        else if(flag == 2) {
            text = "ReportDrivers";
        }

        PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./" + text + ".pdf"));
        pdfDoc.addNewPage();
        Document doc = new Document(pdfDoc);
        Paragraph paragraph = new Paragraph("Report");
        doc.add(paragraph);

        Table table = new Table(column.length);
        table.setFont(font);

        for(String s: column) {
          // table.addCell(new Cell().add(s));
        }

        for(int i = 0;i < model.getRowCount();i++) {
            for(int j = 0;j < model.getColumnCount();j++) {
             // table.addCell(new Cell().add(model.getValueAt(i,j).toString()));
            }
        }

        doc.add(table);
        doc.close();

    }

    /**
     * method for create report in HTML
     * @param model object type of DefaultTableModel
     */
    void createPriceHTML(DefaultTableModel model) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("./ReportInfo.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>ID<TH>Team<TH>Designer<TH>Chassis<TH>Power plant<TH>Driver 1<TH>Driver 2<TH>Status</TR>");
        for(int i = 0; i < model.getRowCount(); i++) {
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
    }

    /**
     * method for create report in HTML
     * @param model object type of DefaultTableModel
     */
    void createPersonalHTML(DefaultTableModel model) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("./ReportDrivers.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>ID<TH>Driver<TH>Team</TR>");
        for(int i = 0; i < model.getRowCount(); i++) {
            pw.println("<TR><TD>" + (String) model.getValueAt(i,0)
                    + "<TD>" + (String) model.getValueAt(i,1)
                    + "<TD>" + (String) model.getValueAt(i,2));
        }
        pw.println("</TABLE>");
        pw.close();
    }
}
