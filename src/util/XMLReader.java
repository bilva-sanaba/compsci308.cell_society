package util;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import cellsociety.CAException;
/**
 * 
 * @author Justin Yang
 * @author Mike Liu
 *
 */
public class XMLReader {
    
    public static final String TYPE_ATTRIBUTE = "type";
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
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
            return handleMissingTag(tagName);
        }
    }
	
	private String handleMissingTag(String tagName) {
	    if(tagName.equals(CAData.DATA_FIELDS.get(0))
	            || tagName.equals(CAData.DATA_FIELDS.get(3))
	            || tagName.equals(CAData.DATA_FIELDS.get(4))) {
	        throw new CAException(CAException.MISSING_FIELD, tagName);
	    }
        return "";
    }
	
    private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			throw new CAException(e);
		}
	}
}