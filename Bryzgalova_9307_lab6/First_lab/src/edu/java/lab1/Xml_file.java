package edu.java.lab1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.lang.Exception;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class for save or load data from XML file
 */
public class Xml_file {

    private static String fileNameOpen;
    private static String fileNameSave;
    private static String tags[] = {"Team", "Designer", "Chassis","Track","Power_plant","Driver_1", "Driver_2","Status"};

    /**
     * method getting document for open
     */
    private static Document getDocumentOpen() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.parse(new File(fileNameOpen));
        } catch (Exception exception) {
            throw new Exception("XML parsing error!");
        }

    }

    /**
     * method getting document for save
     */
    private static Document getDocumentSave() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.newDocument();

        } catch (Exception exception) {
            throw new Exception("XML parsing error!");
        }
    }

    /**
     * method for open xml document
     * @param model - model of table
     * @param table - object type of JTable
     * @param frame - object type of window
     */
    public void xmlOpen(DefaultTableModel model, JTable table,JFrame frame) {

        FileDialog openXml = new FileDialog(frame,"Load File",FileDialog.LOAD);
        openXml.setFile(".xml");
        openXml.setVisible(true);
        fileNameOpen = openXml.getDirectory() + openXml.getFile();
        if(fileNameOpen == null) return;

        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            Document doc = getDocumentOpen();
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("books");

            String s[] = new String[tags.length];
            for(int i = 0;i < list.getLength();i++) {
                Node elem = list.item(i);
                NamedNodeMap atr = elem.getAttributes();
                for(int j = 0;j < tags.length;j++) {
                    s[j] = atr.getNamedItem(tags[j]).getNodeValue();
                }
                model.addRow(s);
            }
        }catch(SAXException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * method for save xml document
     * @param model - model of table
     * @param table - object type of JTable
     * @param frame - object type of window
     * @throws Exception
     */
    public void xmlSave(DefaultTableModel model, JTable table,JFrame frame) throws Exception {
        FileDialog saveXml = new FileDialog(frame,"Save File",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setVisible(true);
        fileNameSave = saveXml.getDirectory() + saveXml.getFile();
        if(fileNameSave == null) return;

        Document doc = getDocumentSave();
        Node booklist = doc.createElement("bookList");
        doc.appendChild(booklist);
        for(int i = 0; i < model.getRowCount(); i++){
            Element books = doc.createElement("books");
            booklist.appendChild(books);

            for(int j = 0;j < tags.length;j++) {
            	books.setAttribute(tags[j],(String) model.getValueAt(i,j));
            }

        }

        try{
            Transformer trans= TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.METHOD, "xml");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSave)));
        }catch(TransformerConfigurationException e){
            e.printStackTrace();
        }catch(TransformerException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
