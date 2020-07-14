
package restaurant;

//2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import kitchen.Dish;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    // для вывода message в консоль
    public static void writeMessage(String message){
        System.out.println(message);
    }
    
    // для чтения строки с консоли
    public static String readString() throws IOException{
        return reader.readLine();
    }
    
    // просит пользователя выбрать блюдо и добавляет его в список
    public static List<Dish> getAllDishesForOrder() throws IOException{
        ConsoleHelper.writeMessage(Dish.allDishesToString());  //2.1 - выводим в консоль все блюда
        ConsoleHelper.writeMessage("Введите название блюда: ");
        List<Dish> dishesForOrder = new ArrayList<>();
        String userInput;
        while(true){
            userInput = readString();  //ввод строки пользователя
            if (userInput.equalsIgnoreCase("exit")) break;
            
            try{
                Dish d = Dish.valueOf(userInput);
                dishesForOrder.add(d);
            } catch (Exception ex){
                ConsoleHelper.writeMessage("Такого блюда нет в меню");
            }
        }
        
        return dishesForOrder;
    }

}
