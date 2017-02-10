package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
<<<<<<< HEAD
=======
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
>>>>>>> b0cf774c90b622ded2a4cc8f9341c3a1614dfd35

import cellsociety.CAException;

/**
 * 
 * @author Justin Yang
 * @author Mike Liu
 *
 */
public class XMLReader {
<<<<<<< HEAD
	private DocumentBuilder builder;
	private Document doc;
	private int[][] cells;
	private String title;
	private int rows;
	private int columns;
	
	public XMLReader() throws ParserConfigurationException {
			builder = getDocumentBuilder();
	}
	
	public XMLData readData(String fileName) throws SAXException, IOException {
		try {
			doc = builder.parse(new File(fileName));
			doc.getDocumentElement().normalize();
			
			title = readTag("sim");
			rows = Integer.parseInt(readTag("rows"));
			columns = Integer.parseInt(readTag("columns"));
			cells = readCells();
			
			if (title.equals("Wator")) return readWator();
			else if (title.equals("Segregation")) return readSegregation();
			else if (title.equals("GOL")) return readGOL();
			else return readFire();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
		return null;
	}
=======
    
    public static final String TYPE_ATTRIBUTE = "type";
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
>>>>>>> b0cf774c90b622ded2a4cc8f9341c3a1614dfd35
	
	public CAData readData(File dataFile) {
	    Element root = getRootElement(dataFile);
        if(!isValidFile(root, CAData.DATA_TYPE)) {
            throw new CAException(CAException.WRONG_TYPE, CAData.DATA_TYPE);
        }
        Map<String, String> results = new HashMap<>();
        for(String field: CAData.DATA_FIELDS) {
            results.put(field, getTextValue(root, field));
        }
        CAData data = CAData.getModelData(results);
        for(String field: data.getExtraField()) {
            data.addExtraField(field, getTextValue(root, field));
        }
        return data;
	}

    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new CAException(CAException.LOAD_FAILURE, xmlFile.getPath());
        }
    }
    
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }
    
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
    
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch(ParserConfigurationException e) {
<<<<<<< HEAD
			throw new XMLException(e);
		}
	}
	
	// Debugging
	public static void main(String[] args) {
		try {
			XMLReader reader = new XMLReader();
			try {
				XMLData data = reader.readData("data/sample.xml");
				System.out.println(data.getColumns());
				System.out.println(data.getTitle());
				System.out.println(data.getCells().toString());
			} catch (SAXException | IOException e) {
				throw new XMLException(e);
			}
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
=======
			throw new CAException(e);
>>>>>>> b0cf774c90b622ded2a4cc8f9341c3a1614dfd35
		}
	}
}