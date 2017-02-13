package util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cellsociety.CAException;
import grid.Grid;
import model.Model;

/**
 * 
 * @author Mike Liu
 * @author Justin Yang
 *
 */
public class XMLWriter {
	
	public static final String TYPE_ATTRIBUTE = "type";
	public static final String ROOT = "data";
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static final Transformer TRANSFORMER = getTransformer();
	
	private Document doc;
	private Element root;
	
	public void saveData(Model model, File file) {
	    doc = DOCUMENT_BUILDER.newDocument();
		makeRoot();
		addElement(CAData.DATA_FIELDS.get(0), model.getName());
		addElement(CAData.DATA_FIELDS.get(3), model.getGrid().numRows());
		addElement(CAData.DATA_FIELDS.get(4), model.getGrid().numCols());
		saveCells(model.getGrid());
		writeToFile(file);
	}
	
	private void makeRoot() {
		root = doc.createElement(ROOT);
		root.setAttribute(TYPE_ATTRIBUTE, CAData.DATA_TYPE);
		doc.appendChild(root);
	}
	
	private void addElement(String tagName, int content) {
	    addElement(tagName, Integer.toString(content));
	}
	
	private void addElement(String tagName, String content) {
	    Element element = doc.createElement(tagName);
	    element.appendChild(doc.createTextNode(content));
	    root.appendChild(element);
	}
	
	private void saveCells(Grid grid) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < grid.numRows(); row++) {
			for (int col = 0; col < grid.numCols(); col++) {
			    List<String> elements = Arrays.asList(
			            Integer.toString(row),
			            Integer.toString(col),
			            Integer.toString(grid.get(row,  col).getState().toInt()));
				sb.append(String.join(" ", elements));
				sb.append(" ");
			}
		}
		addElement(CAData.DATA_FIELDS.get(5), sb.toString());
	}
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			throw new CAException(e);
		}
	}
	
	private void writeToFile(File file) {
		DOMSource sourceDocument = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		try {
			TRANSFORMER.transform(sourceDocument, result);
		} catch (TransformerException e) {
			throw new CAException(e);
		}
	}
	
	private static Transformer getTransformer() {
		try {
		    Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			return transformer;
		} catch (TransformerException e) {
			throw new CAException(e);
		}
	}
}
