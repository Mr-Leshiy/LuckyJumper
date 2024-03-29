package com.mygdx.Lucky_Jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.concurrent.*;

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

public class GameInformationFileHandler  {

    private final static String NAME="GameInformation.xml";
    private Document document;
    private final static String version="2.0";



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
        Element points_node =(Element)document.getElementsByTagName("points").item(0);
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

    public void spentNeronPoints(int points)
    {

        Node points_node = document.getElementsByTagName("neuron_points").item(0);
        int result=-points+Integer.valueOf(points_node.getTextContent());

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
        Element points_node = (Element) document.getElementsByTagName("neuron_points").item(0);
        return Integer.valueOf(points_node.getTextContent());

    }

    public int getPrice(String element)
    {
        Element price = (Element) document.getElementsByTagName(element).item(0);
        return Integer.valueOf(price.getElementsByTagName("current_price").item(0).getTextContent());

    }

    public void setPrice(String element,int new_price)
    {
        Element price = (Element) document.getElementsByTagName(element).item(0);

        price.getElementsByTagName("current_price").item(0).setTextContent(Integer.toString(new_price));

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

    public int getLevel(String element)
    {
        Element level = (Element) document.getElementsByTagName(element).item(0);
        return Integer.valueOf(level.getElementsByTagName("current_level").item(0).getTextContent());

    }

    public void setLevel(String element,int new_level)
    {
        Element level = (Element) document.getElementsByTagName(element).item(0);

        level.getElementsByTagName("current_level").item(0).setTextContent(Integer.toString(new_level));

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

    public static void createDocument()
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document=builder.newDocument();
            document.setXmlVersion(version);

            Element game=document.createElement("GAME");
            document.appendChild(game);

            Element gameInfo = document.createElement("gameInfo");
            game.appendChild(gameInfo);

            Element points = document.createElement("points");
            points.setTextContent("0");
            gameInfo.appendChild(points);

            Element neuron_points = document.createElement("neuron_points");
            neuron_points.setTextContent("0");
            gameInfo.appendChild(neuron_points);

            Element shopInfo = document.createElement("shopInfo");
            game.appendChild(shopInfo);

            Element boosters = document.createElement("boosters");
            shopInfo.appendChild(boosters);

            Element clockItem=document.createElement("clock_item");
            boosters.appendChild(clockItem);

            Element price=document.createElement("current_price");
            price.setTextContent("100");
            clockItem.appendChild(price);

            Element level=document.createElement("current_level");
            level.setTextContent("0");
            clockItem.appendChild(level);

            Element platformBoost=document.createElement("platform_booster_item");
            boosters.appendChild(platformBoost);

            Element price1=document.createElement("current_price");
            price1.setTextContent("100");

           platformBoost.appendChild(price1);

            Element level1=document.createElement("current_level");
            level1.setTextContent("0");
            platformBoost.appendChild(level1);

            Element doubleNeuronpoints=document.createElement("double_neuron_points");
            boosters.appendChild(doubleNeuronpoints);
            Element price2=document.createElement("current_price");
            price2.setTextContent("100");

            doubleNeuronpoints.appendChild(price2);

            Element level2=document.createElement("current_level");
            level2.setTextContent("0");
            doubleNeuronpoints.appendChild(level2);



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

    public static void updateDocument()
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Gdx.files.local(NAME).file());

            double version =Double.valueOf(document.getXmlVersion());

            if(version<2)
            {
                Element boosters = (Element) document.getElementsByTagName("boosters").item(0);

                Element doubleNeuronpoints=document.createElement("double_neuron_points");
                boosters.appendChild(doubleNeuronpoints);
                Element price2=document.createElement("current_price");
                price2.setTextContent("100");

                doubleNeuronpoints.appendChild(price2);

                Element level2=document.createElement("current_level");
                level2.setTextContent("0");
                doubleNeuronpoints.appendChild(level2);

            }

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

    public static boolean checkVersion()
    {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Gdx.files.local(NAME).file());
            return version.equals(document.getXmlVersion());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;

    }


}
