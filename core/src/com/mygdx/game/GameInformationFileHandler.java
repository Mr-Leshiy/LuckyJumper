package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Created by alexey on 30.01.17.
 */

public class GameInformationFileHandler {

    private final static String NAME="GameInformation.xml";
    private Document document;


    public GameInformationFileHandler()
    {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(Gdx.files.local(NAME).file());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }


    public int getPoints()
    {
        Node points_node = document.getElementsByTagName("points").item(0);
        return Integer.valueOf(points_node.getTextContent());

    }
    public void setPoints(int points)
    {
        Node points_node = document.getElementsByTagName("points").item(0);
        points_node.setTextContent(Integer.toString(points));

        try {
            FileHandle fileHandle = Gdx.files.local(NAME);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(fileHandle.file()));
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    public void addNeuronPoints(int points)
    {

        Node points_node = document.getElementsByTagName("neuron_points").item(0);
        int result=points+Integer.valueOf(points_node.getTextContent());

        points_node.setTextContent(Integer.toString(result));

        try {
            FileHandle fileHandle = Gdx.files.local(NAME);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(fileHandle.file()));
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }



    }

    public int getNeuronsPoints()
    {
        Node points_node = document.getElementsByTagName("neuron_points").item(0);
        return Integer.valueOf(points_node.getTextContent());
    }

    public static void createDocument()
    {

        try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document=builder.newDocument();

        Element gameInfo = document.createElement("gameInfo");
        document.appendChild(gameInfo);

        Element points = document.createElement("points");
        points.setTextContent("0");
        gameInfo.appendChild(points);

        Element neuron_points = document.createElement("neuron_points");
        neuron_points.setTextContent("0");
        gameInfo.appendChild(neuron_points);


        FileHandle fileHandle = Gdx.files.local(NAME);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(fileHandle.file()));




    }

        catch (Exception e)
        {
            e.printStackTrace();

        }


    }





}
