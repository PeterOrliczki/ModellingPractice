package com.codecool.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class XMLWriting implements Serializable {

    public void writeRooms(ArrayList<Room> roomItems, String type) throws ParserConfigurationException, TransformerException {
        DocumentBuilder docBuilder = null;
        Document doc = null;
        Element rootElement = null;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docFactory.newDocumentBuilder();

        // root elements
        doc = docBuilder.newDocument();
        rootElement = doc.createElement("Items");

        doc.appendChild(rootElement);
        for (int i = 0; i < roomItems.size(); i++) {
            Element node = doc.createElement("Item");
            rootElement.appendChild(node);

            Element itemName = doc.createElement("itemName");
            itemName.appendChild(doc.createTextNode(roomItems.get(i).getName()));
            node.appendChild(itemName);

            Element roomType = doc.createElement("roomLocation");
            roomType.appendChild(doc.createTextNode(String.valueOf(roomItems.get(i).getRoomLocation())));
            node.appendChild(roomType);

            // writing content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = null;
            if (type.equals("Items")) {
                result = new StreamResult(new File("./RoomsItems.xml"));
            }

            transformer.transform(source, result);
            // its only in for testing, only main can print
            System.out.println(roomItems.get(i).getName() + " " + roomItems.get(i).getRoomLocation() + " saved to file!");

        }
    }
}
