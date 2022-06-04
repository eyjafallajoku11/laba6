package utility.bareCommand;

import static java.lang.System.out;

public class BareCommandHelp extends BareCommand {
    public String execute(){
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert id {element} : добавить новый элемент с заданным ключом(id)\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 8 команд (без их аргументов)\n" +
                "replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого\n" +
                "remove_all_by_government government : удалить из коллекции все элементы, значение поля government которого эквивалентно заданному\n" +
                "filter_less_than_governor governor : вывести элементы, значение поля governor которых меньше заданного\n" +
                "print_descending : вывести элементы коллекции в порядке убывания");

    }
}
