package utility.dataCommand;

import gorod.City;
import utility.HashMapController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class DataCommandSave extends DataCommand {
    public void execute(String data) {
        try {
            FileWriter fw = new FileWriter(data, false);
            BufferedWriter buffWriter = new BufferedWriter(fw);
            buffWriter.write("<collection>");
            buffWriter.newLine();
            for (Map.Entry<Long, City> entry : HashMapController.getMap().entrySet()) {
                buffWriter.write("<City id=\"" + entry.getKey() + "\">");
                buffWriter.newLine();
                buffWriter.write("<name>" + entry.getValue().getName() + "</name>");
                buffWriter.newLine();
                buffWriter.write("<coordinates>");
                buffWriter.newLine();
                buffWriter.write("<x>" + entry.getValue().getCoordinates().getX() + "</x>");
                buffWriter.newLine();
                buffWriter.write("<y>" + entry.getValue().getCoordinates().getY() + "</y>");
                buffWriter.newLine();
                buffWriter.write("</coordinates>");
                buffWriter.newLine();
                buffWriter.write("<creationDate>" + entry.getValue().getCreationDate().toString() + "</creationDate>");
                buffWriter.newLine();
                buffWriter.write("<area>" + entry.getValue().getArea() + "</area>");
                buffWriter.newLine();
                buffWriter.write("<population>" + entry.getValue().getPopulation().toString() + "</population>");
                buffWriter.newLine();
                if (Objects.isNull(entry.getValue().getMetersAboveSeaLevel())) {
                    buffWriter.write("<metersAboveSeaLevel></metersAboveSeaLevel>");
                } else
                    buffWriter.write("<metersAboveSeaLevel>" + entry.getValue().getMetersAboveSeaLevel().toString() + "</metersAboveSeaLevel>");
                buffWriter.newLine();
                if (Objects.isNull(entry.getValue().getClimate())) {
                    buffWriter.write("<climate></climate>");
                } else buffWriter.write("<climate>" + (entry.getValue().getClimate().toString()) + "</climate>");
                buffWriter.newLine();
                buffWriter.write("<government>" + entry.getValue().getGovernment().toString() + "</government>");
                buffWriter.newLine();
                if (Objects.isNull(entry.getValue().getStandardOfLiving())) {
                    buffWriter.write("<standardOfLiving></standardOfLiving>");
                } else
                    buffWriter.write("<standardOfLiving>" + entry.getValue().getStandardOfLiving().toString() + "</standardOfLiving>");
                buffWriter.newLine();
                buffWriter.write("<government>" + entry.getValue().getGovernment().toString() + "</government>");
                buffWriter.newLine();
                if (Objects.isNull(entry.getValue().getGovernor())) {
                    buffWriter.write("<governor>\n" +
                            "<govName></govName>\n" +
                            "<birthday></birthday>\n" +
                            "</governor>");
                } else {
                    buffWriter.write("<governor>");
                    buffWriter.write("<govName>" + entry.getValue().getGovernor().getName() + "</govName>");
                    if (Objects.isNull(entry.getValue().getGovernor().getBirthday())) {
                        buffWriter.write("<birthday></birthday>");
                    } else
                        buffWriter.write("<birthday>" + entry.getValue().getGovernor().getBirthday().toString() + "</birthday>");
                    buffWriter.write("</governor>");
                }
                buffWriter.newLine();
                buffWriter.write("</City>");
                buffWriter.newLine();
            }
            buffWriter.write("</collection>");
            buffWriter.flush();
            buffWriter.close();
        }
        catch (IOException e){
            System.out.println("ты шо-то напутал");
        }
    }
}