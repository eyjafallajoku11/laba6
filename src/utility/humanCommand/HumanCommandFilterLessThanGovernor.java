package utility.humanCommand;

import gorod.City;
import gorod.Human;
import utility.HashMapController;

import static java.lang.System.out;


public class HumanCommandFilterLessThanGovernor extends HumanCommand {
    public void execute(Human governor) {
//        try {
            out.println("фильтруем пацанов");
            for (long keys : HashMapController.ID_set()) {
                City city = HashMapController.get_by_ID(keys);
                if (city.getGovernor().compareTo(governor) > 0) {
                    out.println(city);
                    out.println();
                }
            }
//        } catch (NullPointerException e){out.println("а где");}
    }
}
