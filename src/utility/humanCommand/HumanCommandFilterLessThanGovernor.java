package utility.humanCommand;

import gorod.City;
import gorod.Human;
import utility.HashMapController;

import static java.lang.System.out;


public class HumanCommandFilterLessThanGovernor extends HumanCommand {
    public String execute(Human governor) {
        StringBuilder output = new StringBuilder();
        for (long keys : HashMapController.ID_set()) {
            City city = HashMapController.get_by_ID(keys);
            if (city.getGovernor().compareTo(governor) > 0) {
                output.append(city);
                output.append("\n");
            }
        }
        return output.toString();
    }
}
