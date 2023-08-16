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
import java.io.IOException;

/**
 * Class for save data from XML file
 */
public class Xml_file {

    private static String fileNameSave;
    private static String tags[];

    Xml_file(int flag) {
        if(flag == 1) {
            tags = new String[]{"ID", "Team", "Designer", "Chassis", "Pover plant", "Driver 1", "Driver 2", "Status"};
        }
        else if(flag == 2) {
            tags = new String[]{"ID", "Driver", "Team"};
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
     * method for save xml document
     * @param model - model of table
     * @param frame - object type of window
     * @throws Exception standart exception
     */
    public void xmlSaveInfo(DefaultTableModel model,JFrame frame) throws Exception {
        FileDialog saveXml = new FileDialog(frame,"Save File",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setVisible(true);
        fileNameSave = saveXml.getDirectory() + saveXml.getFile();
        if(fileNameSave == null) return;

        Document doc = getDocumentSave();
        Node list = doc.createElement("rasingList");
        doc.appendChild(list);
        for(int i = 0; i < model.getRowCount(); i++){
            Element weapon = doc.createElement("rasing");
            list.appendChild(weapon);

            for(int j = 0;j < tags.length;j++) {
                weapon.setAttribute(tags[j],(String) model.getValueAt(i,j));
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

    public void xmlSaveDriver(DefaultTableModel model,JFrame frame) throws Exception {
        FileDialog saveXml = new FileDialog(frame,"Save File",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setVisible(true);
        fileNameSave = saveXml.getDirectory() + saveXml.getFile();
        if(fileNameSave == null) return;

        Document doc = getDocumentSave();
        Node weaponlist = doc.createElement("driverList");
        doc.appendChild(weaponlist);
        for(int i = 0; i < model.getRowCount(); i++){
            Element weapon = doc.createElement("driver");
            weaponlist.appendChild(weapon);

            for(int j = 0;j < tags.length;j++) {
                weapon.setAttribute(tags[j],(String) model.getValueAt(i,j));
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

