package com.codecool.api;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class RoomsXML extends XMLParser {
    private ArrayList<Room> items;

    public RoomsXML(String xmlPath) {
        items = new ArrayList<>();
        loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> itemNodes = getElements(rootNode);
        addItems(itemNodes);
    }

    public void addItems(List<Element> itemNodes) {
        for (Element itemNode : itemNodes
        ) {
            List<Element> fieldNodes = getElements(itemNode);
            String name = getString(fieldNodes, "itemName");
            RoomType roomType = RoomType.valueOf(getString(fieldNodes, "roomType"));
            Room downstairsRoom = new DownstairsRoom(name, RoomLocation.DOWNSTAIRS);
            Room upstairsRoom = new UpstairsRoom(name, RoomLocation.UPSTAIRS);
            Room loftRoom = new Loft(name, RoomLocation.LOFT);
            items.add(downstairsRoom);
            items.add(upstairsRoom);
            items.add(loftRoom);
        }
    }

    public String getString(List<Element> elements, String name) {
        for (Element element : elements
        ) {
            if (element.getTagName().equals(name)) {
                return element.getTextContent();
            }
        }
        throw new IllegalStateException();
    }

    public List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childnode = parentNode.getChildNodes().item(i);
            if (childnode instanceof Element) {
                elements.add((Element) childnode);
            }

        }
        return elements;
    }

    public ArrayList<Room> getItems() {
        return items;
    }
}
