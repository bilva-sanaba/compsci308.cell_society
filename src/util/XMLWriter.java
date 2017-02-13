package util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import cellsociety.CAException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import cellsociety.Grid;

public class XMLWriter {
	
	public static final String TYPE_ATTRIBUTE = "type";
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private static final Transformer TRANSFORMER = getTransformer();
	private Document doc = DOCUMENT_BUILDER.newDocument();
	private Element root;
	
	public void saveData(Grid grid) {
		makeRoot();
		makeTitle();
		saveRow(grid);
		saveCol(grid);
		saveCells(grid);
		writeToFile();
	}
	
	private void makeRoot() {
		root = doc.createElement("data");
		root.setAttribute(TYPE_ATTRIBUTE, "CA");
		doc.appendChild(root);
	}
	
	private void makeTitle() {
		// TODO: Implement
	}
	
	private void saveRow(Grid grid) {
		Element xmlRow = doc.createElement("row");
		xmlRow.appendChild(doc.createTextNode(String.valueOf(grid.numRows())));
		root.appendChild(xmlRow);
	}
	
	private void saveCol(Grid grid) {
		Element xmlCol = doc.createElement("row");
		xmlCol.appendChild(doc.createTextNode(String.valueOf(grid.numCols())));
		root.appendChild(xmlCol);
	}
	
	private void saveCells(Grid grid) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < grid.numRows(); i++) {
			for (int j = 0; j < grid.numCols(); j++) {
				sb.append(String.valueOf(i) + " " + String.valueOf(j) + " ");
				sb.append(String.valueOf(grid.get(i,  j).getState()) + " ");
			}
		}
		Element xmlCell = doc.createElement("cell");
		xmlCell.appendChild(doc.createTextNode(sb.toString()));
		root.appendChild(xmlCell);
	}
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			throw new CAException(e);
		}
	}
	
	private void writeToFile() {
		DOMSource sourceDocument = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("data/savedstate.xml"));
		try {
			TRANSFORMER.transform(sourceDocument, result);
		} catch (TransformerException e) {
			throw new CAException(e);
		}
	}
	
	private static Transformer getTransformer() {
		try {
			return TransformerFactory.newInstance().newTransformer();
		} catch (TransformerException e) {
			throw new CAException(e);
		}
	}
}
