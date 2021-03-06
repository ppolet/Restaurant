
package restaurant;

import restaurant.ad.AdvertisementManager;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.ad.NoVideoAvailableException;
import restaurant.kitchen.Order;
import restaurant.kitchen.TestOrder;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.NoAvailableVideoEventDataRow;

public class Tablet{
    private final int number;  //1 - номер планшета, чтобы можно было однозначно установить, откуда поступил заказ.
    private static Logger logger = Logger.getLogger(Tablet.class.getName());   //2.5
    private LinkedBlockingQueue<Order> orderQueue;    //22.4
    
    public Tablet(int number){
        this.number = number;
    }
    
    //22.4
    public void setOrderQueue(LinkedBlockingQueue<Order> order){
        this.orderQueue = order;
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
                //setChanged();
                //notifyObservers(order);
                orderQueue.add(order);  //22.5 - добавляем заказ в очередь
            }
        } catch (NoVideoAvailableException e) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (IOException ex) {
            System.out.println("Exception at tablet: " + ex.getMessage());
            logger.log(Level.SEVERE, "Console is unavailable...", ex);
        }
        return order;  //3.5
    }

    //18.2.4 - который будет случайным образом генерировать заказ со случайными блюдами не общаясь с реальным человеком.
    public void createTestOrder(){
        TestOrder order = null;
        try {
            order = new TestOrder(this);  //Создаем новый заказ на этом планшете
            if(!order.isEmpty()){    //5.5
                ConsoleHelper.writeMessage(order.toString());
                new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();  //8.5 - вызываем видео и передаем ему время готовки заказа в секундах
                //setChanged();
                //notifyObservers(order);
                orderQueue.add(order);  //22.5 - добавляем заказ в очередь
            }
        } catch (NoVideoAvailableException e) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (IOException ex) {
            System.out.println("Exception at tablet: " + ex.getMessage());
            logger.log(Level.SEVERE, "Console is unavailable...", ex);
        }
    }

    @Override
    public String toString() {
        return "Tablet {number = " + number + "}";
    }    
}
