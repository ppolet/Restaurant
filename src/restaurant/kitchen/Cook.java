
package restaurant.kitchen;

//3.1 - он будет готовить

import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.ConsoleHelper;
import restaurant.Restaurant;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;


public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;   //21.1
    private LinkedBlockingQueue<Order> orderQueue;    //22.1
    
    public Cook(String name){
        this.name = name;
    }

    //22.2
    public void setOrderQueue(LinkedBlockingQueue<Order> order){
        this.orderQueue = order;
    }
    
    public boolean isBusy(){
        return busy;
    }

    @Override
    public String toString() {
        return "Cook {" + "name = " + name + '}';
    }

    /*
    -observable - объект, который отправил нам значение
    -arg - само значение, в нашем случае - это объект Order    
    */
    // 20.5 - Удали метод update из класса Cook.
    /*  
    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time: " + order.getTotalCookingTime() + " min.");   //3.3
        //14.4 - Зарегистрируй событие для повара во время приготовления еды.
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes())); 
        setChanged();           //4.4
        notifyObservers(order);  //4.4
    }
    */

    //20.4
    public void startCookingOrder(Order order){
        busy = true;        //21.2 - повар занят
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time: " + order.getTotalCookingTime() + " min.");   //3.3
        try {
            Thread.sleep(order.getTotalCookingTime()*10);   //21.3 - имитация задержки при приготовлении блюда, поставь слип в 10-кратном размере от времени приготовления заказа.
        } catch (InterruptedException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        //14.4 - Зарегистрируй событие для повара во время приготовления еды.
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes())); 
        setChanged();           //4.4
        notifyObservers(order);  //4.4
        busy = false;       //21.2
    }

    @Override
    public void run() {
        while(true){
            if(!orderQueue.isEmpty()){  //есть заказы
                if (!this.busy){            //повар не занят, берет заказ
                    startCookingOrder(orderQueue.poll());   //теперь повар занят и готовит заказ, заказ удаляется из очереди
                }
            }
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
    
}
