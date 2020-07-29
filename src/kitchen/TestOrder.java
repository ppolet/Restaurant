
package kitchen;

//18.2.4 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import restaurant.Tablet;

//18.2.4 
public class TestOrder extends Order {
    
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }
    
    //переопредели initDishes в классе-наследнике TestOrder. Сделай инициализацию случайным набором блюд.
    protected void initDishes(){
        dishes = new ArrayList<>();
        dishes.addAll(Arrays.asList(Dish.values()));              // записываем все блюда в ArrayList
        Collections.shuffle(dishes);                              // сортируем случайным порядком
        int countDishes = (int)(Math.random()*dishes.size())+1;   // Кол-во блюд в заказе
        dishes = dishes.subList(0, countDishes);                  // Записываем это кол-во блюд в dishes
    }
}
