
package restaurant;

//20

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.kitchen.Cook;
import restaurant.kitchen.Order;
import restaurant.statistic.StatisticManager;

public class OrderManager implements Observer{
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    
    //21.5. - демон-трэд. Логика метода run: каждые 10 миллисекунд проверять очередь. Если в очереди есть заказы, то найти свободных поваров и передать
    // им заказы (метод startCookingOrder), если нет свободных поваров или нет заказов в очереди, то ждать дальше.
    public OrderManager(){
        Thread daemonThread = new Thread() {
            @Override
            public void run(){
                while(true){
                    if(!orderQueue.isEmpty()){  //есть заказы
                        for(Cook cook: StatisticManager.getInstance().getCooks()){
                            if (!cook.isBusy()){
                                cook.startCookingOrder(orderQueue.poll());   //повар занят и готовит заказ, заказ удаляется из очереди
                            }
                        }
                    }
                    try {
                        sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println("Exception: " + ex.getMessage());
                    }
                }
            }
        };
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order)arg;
        orderQueue.add(order);
    }
}
