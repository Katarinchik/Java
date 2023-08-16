import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.lang.Exception;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Class for save or load data from XML file
 */
public class FileOperation {
    private static final Logger log_OpenFile = Logger.getLogger(FileOperation.class);
    private static final Logger log_SaveFile = Logger.getLogger(FileOperation.class);
    private static String fileNameOpen;
    private static String fileNameSave;
    private static String tags[] = {"country", "manufacturer", "calibr", "gunType","countAmmo","weight","cost","aviabillity"};

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
     */
    public void xmlOpen(DefaultTableModel model) {
        log_OpenFile.info("Open xml file");
        fileNameOpen = "./data.xml";
        model.setRowCount(0);

        try {
            Document doc = getDocumentOpen();
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("weapon");

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
            log_OpenFile.error("Error! SAXException");
            e.printStackTrace();
        }catch(Exception e){
            log_OpenFile.error("Error! Exception");
            e.printStackTrace();
        }

    }

    /**
     * method for save xml document
     * @param model - model of table
     * @throws Exception standart exception
     */
    public void xmlSave(DefaultTableModel model) throws Exception {
        log_SaveFile.info("Save xml file");
        fileNameSave = "./data.xml";

        Document doc = getDocumentSave();
        Node weaponlist = doc.createElement("weaponList");
        doc.appendChild(weaponlist);
        for(int i = 0; i < model.getRowCount(); i++){
            Element weapon = doc.createElement("weapon");
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
            log_SaveFile.error("TransformerConfigurationException");
        }catch(TransformerException e){
            log_SaveFile.error("TransformerException");
            e.printStackTrace();
        }catch(IOException e){
            log_SaveFile.error("Exception");
            e.printStackTrace();
        }
    }

}
