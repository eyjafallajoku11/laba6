package utility.creatorCommand;

import utility.CityCreator;
import utility.HashMapController;

import java.util.Objects;

import static java.lang.System.out;
public class CreatorCommandInsert extends CreatorCommand {
    public void execute(String[] creatorData){
        try {
            if (!HashMapController.contains_ID(Long.parseLong(creatorData[12]))) {
                try {
                    CityCreator creator = new CityCreator(creatorData);
                    creator.create_city();
                    creator.add_city_to_map();
                    if (Objects.isNull(HashMapController.getInitialisation())) {
                        HashMapController.initialise();
                    }
                } catch (NullPointerException e)
                {System.out.println("не хватает обязательных переменных, город не создан");}
                catch (Exception e){System.out.println("ошибка в формате данных, город не создан");}
                //[x,y,name,creation date,area,population,metersAboveSeaLevel,climate,government,standardOfLiving,govName,birthday,id,1/0 boolean Governor]
            } else {
                out.println("этот ключ уже занят");
            }
        }
        catch (NumberFormatException e) {
            out.println("че с ключом, это цифра должна быть");
        }
        catch (Exception e){
            out.println("совсем фигня какая-то");
        }
    }
}
