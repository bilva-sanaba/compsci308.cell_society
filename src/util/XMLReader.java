package util;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.Document;

public class XMLReader {
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
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private GOLData readGOL() {
		GOLData golData = new GOLData(rows, columns);
		golData.setTitle(title);
		golData.setCells(cells);
		return golData;
	}
	
	private SegregationData readSegregation() {
		SegregationData segregationData = new SegregationData(rows, columns);
		segregationData.setTitle(title);
		segregationData.setCells(cells);
		segregationData.setThreshold(Integer.parseInt(readTag("threshold")));
		return segregationData;
	}
	
	private WatorData readWator() {
		WatorData watorData = new WatorData(rows, columns);
		watorData.setTitle(title);
		watorData.setCells(cells);
		return watorData;
	}
	
	private FireData readFire() {
		FireData fireData = new FireData(rows, columns);
		fireData.setTitle(title);
		fireData.setCells(cells);
		fireData.setProbCatch(Integer.parseInt(readTag("probcatch")));
		return fireData;
	}
	
	private int[][] readCells() {
		String cellString = readTag("cells");
		int[][] parsedCells = new int[rows][columns];
		Scanner sc = new Scanner(cellString);
		while (sc.hasNext()) parsedCells[sc.nextInt()][sc.nextInt()] = sc.nextInt();
		sc.close();
		return parsedCells;
	}
	
	private String readTag(String tag) {
		return doc.getElementsByTagName(tag).item(0).getTextContent();
	}
	
	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch(ParserConfigurationException e) {
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
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}