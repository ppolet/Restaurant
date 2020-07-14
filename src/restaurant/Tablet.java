
package restaurant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kitchen.Order;

public class Tablet {
    private final int number;  //1 - номер планшета, чтобы можно было однозначно установить, откуда поступил заказ.
    private static Logger logger = Logger.getLogger(Tablet.class.getName());   //2.5
    
    public Tablet(int number){
        this.number = number;
    }
    
    //1.3 - будет создавать заказ из тех блюд, которые выберет пользователь.
    public void createOrder(){
        //2.6
        try {
            Order order = new Order(this);  //Создаем новый заказ на этом планшете
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Console is unavailable...", ex);
        }
        
    }

    @Override
    public String toString() {
        return "Tablet {number = " + number + "}";
    }
    

}
