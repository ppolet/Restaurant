
package restaurant;

import ad.AdvertisementManager;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import kitchen.Order;

public class Tablet extends Observable{
    private final int number;  //1 - номер планшета, чтобы можно было однозначно установить, откуда поступил заказ.
    private static Logger logger = Logger.getLogger(Tablet.class.getName());   //2.5
    
    public Tablet(int number){
        this.number = number;
    }
    
    //1.3 - будет создавать заказ из тех блюд, которые выберет пользователь.
    public Order createOrder(){
        //2.6
        Order order = null;
        try {
            order = new Order(this);  //Создаем новый заказ на этом планшете
            if(!order.isEmpty()){    //5.5
                ConsoleHelper.writeMessage(order.toString());
                new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();  //8.5 - вызываем видео и передаем ему время готовки заказа в секундах
                setChanged();
                notifyObservers(order);
            }
        } catch (IOException ex) {
            System.out.println("Exception at tablet: " + ex.getMessage());
            logger.log(Level.SEVERE, "Console is unavailable...", ex);
        }
        return order;  //3.5
    }

    @Override
    public String toString() {
        return "Tablet {number = " + number + "}";
    }
    

}
