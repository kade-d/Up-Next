package bsu.edu.cs222;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

    public String findXMLPath(){
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\GameProgress.xml");

        return filePath;
    }


    public ArrayList<Game> readXML(String filePath) {

        ArrayList<Game> gameProgress = new ArrayList<>();
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(filePath);

            NodeList nList = dom.getElementsByTagName("game");

            String gameName = "";
            String gameCompleted = "";

            for(int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    gameName = element.getElementsByTagName("gameName").item(0).getTextContent();
                    gameCompleted = element.getElementsByTagName("gameCompleted").item(0).getTextContent();
                }
                Boolean bGameCompleted = Boolean.parseBoolean(gameCompleted);
                Game newGame = new Game(gameName, bGameCompleted);
                gameProgress.add(newGame);
            }


        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        return gameProgress;
    }

    public void saveToXML(String filePath, ArrayList<Game> gameProgress) {
        Document dom;
        Element e;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            Element rootEle = dom.createElement("gameProgress");
            dom.appendChild(rootEle);


            for(Game game: gameProgress){
                // create data elements and place them under root
                Element rootEle2 = dom.createElement("game");
                rootEle.appendChild(rootEle2);
                e = dom.createElement("gameName");
                String gameName = game.getGameName();
                e.appendChild(dom.createTextNode(gameName));
                rootEle2.appendChild(e);
                e = dom.createElement("gameCompleted");
                Boolean gameCompleted = game.getGameCompleted();
                e.appendChild(dom.createTextNode(gameCompleted.toString()));
                rootEle2.appendChild(e);
            }

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(filePath)));

            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

}
