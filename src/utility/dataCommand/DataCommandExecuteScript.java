package utility.dataCommand;

import gorod.Human;
import utility.CityCreator;
import utility.CommandManager;
import utility.Request;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;

public class DataCommandExecuteScript extends DataCommand {
    public static String readByLine(BufferedInputStream Buf_in) {
        StringBuilder response = new StringBuilder();
        String[] str_out = new String[2];
        try {
            int in;
            char inChar;
            short t = 0;
            do {
                in = Buf_in.read();
                if (in != -1) {
                    if (in==32 || in==10){
                        str_out[t]=response.toString();
                        t++;
                        response = new StringBuilder();
                        if (in == 10){
                            if (str_out[0].equals("insert") || str_out[0].equals("remove_lower") || str_out[0].equals("replace_if_greater") || str_out[0].equals("update")){
                                String[] creator = new String[12];
                                short t_data = 0;
                                do {
                                    try{
                                        in = Buf_in.read();
                                    } catch (IOException e ){
                                        return ("что-то с файлом");
                                    }
                                    if (in == 10){
                                        if (t_data == 10 ){
                                            if (response.toString().equals("")){creator[13] = "0";}
                                            else {creator[13] = "1";}
                                        }
                                        creator[t_data] = response.toString();
                                        response = new StringBuilder();
                                        t_data++;
                                    }
                                    else {
                                        inChar = (char) in;
                                        response.append(inChar);
                                    }
                                } while (t_data<12);
                                creator[12] = str_out[1];
                                CommandManager.execute(new Request(str_out[0], creator));
                            }
                            else if (str_out[0].equals("filter_less_than_governor")){
                                do {
                                    try{
                                        in = Buf_in.read();
//                                        out.println((char) in);
                                    } catch (IOException e ){
                                        return ("чет фигня какая-то со скриптом");
                                    }
                                    if (in != 10){
                                        inChar= (char) in;
                                        response.append(inChar);
//                                        out.println(response);
                                    }
                                }while (in!= 10);
                                if (!response.toString().equals("")){
                                    Human governor = new Human(response.toString());
                                    response = new StringBuilder();
                                    do {
                                        try{
                                            in = Buf_in.read();
//                                            out.println((char) in);
                                        } catch (IOException e ){
                                            return ("чет фигня какая-то со скриптом");
                                        }
                                        if (in != 10){
                                            inChar = (char) in;
                                            response.append(inChar);
//                                            out.println(response);
                                        }
                                    }while (in!= 10);
                                    if (!response.toString().equals("")){
                                        try{
                                            String pattern = "dd-MM-yyyy HH:mm:ss";
                                            DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
                                            governor.setBirthday(ZonedDateTime.parse(response.toString(),Parser));
                                        } catch (Exception e) {
                                            return ("неправильная дата");
                                        }
                                    }
                                    CommandManager.execute(new Request( "filter_less_than_governor", governor));
                                }
                                response = new StringBuilder();
                            }
                            else {
                                CommandManager.execute(new Request(str_out[0],str_out[1]));
                            }
                            str_out[0]=null;
                            str_out[1]=null;
                            t=0;
                        }                   }
                    else {
                        inChar = (char) in;
                        response.append(inChar);
                    }
                }
            } while (in != -1);
        } catch (IOException e) {
            return ("что-то с файлом");
        }
        return "";
    }

    public String execute(String data) {
        try (BufferedInputStream inp_str = new BufferedInputStream(new FileInputStream(data))) {
            return readByLine(inp_str);
        } catch (FileNotFoundException e) {
            return ("файла по этому адресу нет");
        } catch (IOException e) {
            return ("что-то не так");
        }
    }
}
