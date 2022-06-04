package utility.dataCommand;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import utility.CityCreator;
import utility.HashMapController;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.util.Objects;

import static java.lang.System.out;

public class DataCommandOpenFile extends DataCommand {
    public String execute(String data){
        if (Objects.isNull(HashMapController.getInitialisation())) {
            HashMapController.initialise();
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        CityCreator inp=new CityCreator();
        try (BufferedInputStream inp_str = new BufferedInputStream(new FileInputStream(data))) {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inp_str);
            if (doc.hasChildNodes()) {
                readNote(doc.getChildNodes(),inp);
            }
            return ("collection imported");
        } catch (FileNotFoundException e){
            return ("файла по этому адресу нет");
        } catch (NullPointerException e){
            return("а че открывать то");
        }
        catch (ParserConfigurationException | SAXException e) {
            return ("в файле ошибка, как его читать-то");
        }
        catch (Exception e){
            return ("вообще всё плохо");}
    }
    private static void readNote(NodeList nodeList, CityCreator inp) {
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node tempNode = nodeList.item(j);
            if (tempNode.hasAttributes()) {
                NamedNodeMap nodeMap = tempNode.getAttributes();
                for (int i = 0; i < nodeMap.getLength(); i++) {
                    Node node = nodeMap.item(i);
                    if (node.getNodeName().equals("id")) {
                        inp.set_data(12,node.getNodeValue());
                    }
                }
            }
            if (tempNode.hasChildNodes()) {
                readNote(tempNode.getChildNodes(),inp);
            }
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (!tempNode.getTextContent().equals("")){
                    switch (tempNode.getNodeName()) {
                        case "x":
//                            System.out.println("coord x = " + tempNode.getTextContent());
                            inp.set_data(0, tempNode.getTextContent());
                            break;
                        case "y":
//                            System.out.println("coord y = " + tempNode.getTextContent());
                            inp.set_data(1, tempNode.getTextContent());
                            break;
                        case "name":
//                            System.out.println("name = " + tempNode.getTextContent());
                            inp.set_data(2, tempNode.getTextContent());
                            break;
                        case "creationDate":
//                            System.out.println("creationDate = " + tempNode.getTextContent());
                            inp.set_data(3, tempNode.getTextContent());
                            break;
                        case "area":
//                            System.out.println("area = " + tempNode.getTextContent());
                            inp.set_data(4, tempNode.getTextContent());
                            break;
                        case "population":
//                            System.out.println("population = " + tempNode.getTextContent());
                            inp.set_data(5, tempNode.getTextContent());
                            break;
                        case "metersAboveSeaLevel":
//                            System.out.println("metersAboveSeaLevel = " + tempNode.getTextContent());
                            inp.set_data(6, tempNode.getTextContent());
                            break;
                        case "climate":
//                            System.out.println("climate = " + tempNode.getTextContent());
                            inp.set_data(7, tempNode.getTextContent());
                            break;
                        case "government":
//                            System.out.println("government = " + tempNode.getTextContent());
                            inp.set_data(8, tempNode.getTextContent());
                            break;
                        case "standardOfLiving":
//                            System.out.println("standardOfLiving = " + tempNode.getTextContent());
                            inp.set_data(9, tempNode.getTextContent());
                            break;
                        case "governor":
                            inp.set_data(13, "1");
                            break;
                        case "govName":
//                            System.out.println("govName = " + tempNode.getTextContent());
                            inp.set_data(10, tempNode.getTextContent());
                            break;
                        case "birthday":
//                            System.out.println("birthday = " + tempNode.getTextContent());
                            inp.set_data(11, tempNode.getTextContent());
                            break;
                        case "City":
//                            System.out.println();

                        try{
                            inp.create_city();
                            inp.add_city_to_map();
                        } catch (NullPointerException e)
                        {System.out.println("не хватает обязательных переменных, город не создан");}
                        catch (Exception e){System.out.println("ошибка в формате данных, город не создан");}
                        inp.clear_data();
                    }
                }
            }
        }
    }
}