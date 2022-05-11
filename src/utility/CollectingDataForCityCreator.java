package utility;

import java.io.BufferedInputStream;
import java.util.Objects;

import static java.lang.System.in;
import static java.lang.System.out;
import static utility.readline.readLine;

public class CollectingDataForCityCreator {
    public static CityCreator execute(String id) {
        CityCreator creator = new CityCreator();
        out.println("* - обязательные переменные");
        out.print("coord x* (double >-251): ");
        creator.set_data(0, readLine(new BufferedInputStream(in)));
        out.print("coord y* (double >-310): ");
        creator.set_data(1, readLine(new BufferedInputStream(in)));
        out.print("Сity name*: ");
        creator.set_data(2, readLine(new BufferedInputStream(in)));
        out.print("creation date* (dd-MM-yyyy): ");
        creator.set_data(3, readLine(new BufferedInputStream(in)));
        out.print("area* (double): ");
        creator.set_data(4, readLine(new BufferedInputStream(in)));
        out.print("population* (int): ");
        creator.set_data(5, readLine(new BufferedInputStream(in)));
        out.print("meters above sea level: ");
        creator.set_data(6, readLine(new BufferedInputStream(in)));
        out.print("climate(HUMIDSUBTROPICAL|OCEANIC|POLAR_ICECAP|RAIN_FOREST|SUBARCTIC): ");
        creator.set_data(7, readLine(new BufferedInputStream(in)));
        out.print("government*(ITMOCRACY|KLEPTOCRACY|MERITOCRACY|MONARCHY|TELLUROCRACY): ");
        creator.set_data(8, readLine(new BufferedInputStream(in)));
        out.print("standard of living(VERY_LOW|LOW|VERY_HIGH): ");
        creator.set_data(9, readLine(new BufferedInputStream(in)));
        out.print("Governor name: ");
        String t = readLine(new BufferedInputStream(in));
        if (t.equals("")) {
            creator.set_data(13, "0");
        } else {
            creator.set_data(13, "1");
            creator.set_data(10, t);
            out.print("Governor birthday(dd-MM-yyyy hh:mm:ss): ");
            creator.set_data(11, readLine(new BufferedInputStream(in)));
        }
        if (Objects.isNull(id)){id="";}
        creator.set_data(12, id);
        return creator;
    }

    public static CityCreator execute() {
        return execute(null);
    }
}
