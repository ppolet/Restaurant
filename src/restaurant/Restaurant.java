
package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import restaurant.kitchen.Cook;
import restaurant.kitchen.Order;
import restaurant.kitchen.Waiter;
import restaurant.statistic.StatisticManager;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;   //18.1
    private final static LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();    //22.1
            
    public static void main(String[] args){
        // создадим 2 повара
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Armando");
        cook1.setOrderQueue(ORDER_QUEUE);  //22.2
        cook2.setOrderQueue(ORDER_QUEUE);  //22.2
        Thread threadCook1 = new Thread(cook1); //22.6
        Thread threadCook2 = new Thread(cook2); //22.6
        //22.6
        threadCook1.start();
        threadCook2.start();

        //StatisticManager.getInstance().register(cook1);   //19.3
        //StatisticManager.getInstance().register(cook2);   //19.3

//        Tablet tablet = new Tablet(1);
//        tablet.addObserver(cook);

        //19.5 - Создай список объектов-планшетов 5 штук, инициализируйте его в цикле.
        List<Tablet> tablets = new ArrayList<>();
//        OrderManager orderManager = new OrderManager();  //20.6
        for(int i=0; i<5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setOrderQueue(ORDER_QUEUE);          //22.4
//            tablet.addObserver(orderManager);           //20.6
            tablets.add(tablet);
        }
        
        
        Waiter waiter = new Waiter();  //4.5
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        
//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();

        RandomOrderGeneratorTask orderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(orderGeneratorTask);
        thread.start();
        try {
            Thread.sleep(1000);     // 19.7 - 1 сек задержки
        } catch (InterruptedException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        thread.interrupt();         // 19.7 - прерываем
        
        threadCook1.interrupt();
        threadCook2.interrupt();
        
        //15.3
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        
    }
    
}
