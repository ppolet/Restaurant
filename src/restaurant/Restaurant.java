
package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.kitchen.Cook;
import restaurant.kitchen.Waiter;
import restaurant.statistic.StatisticManager;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;   //18.1
            
    public static void main(String[] args) {
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Armando");
        StatisticManager.getInstance().register(cook1);   //19.3
        StatisticManager.getInstance().register(cook2);   //19.3

//        Tablet tablet = new Tablet(1);
//        tablet.addObserver(cook);

        //19.5 - Создай список объектов-планшетов 5 штук, инициализируйте его в цикле.
        List<Tablet> tablets = new ArrayList<>();
        OrderManager orderManager = new OrderManager();  //20.6
        for(int i=0; i<5; i++){
            Tablet tablet = new Tablet(i);
            tablet.addObserver(orderManager);           //20.6
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

        //15.3
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        
    }
    
}
