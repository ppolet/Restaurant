
package restaurant;

import kitchen.Cook;
import kitchen.Waiter;

public class Restaurant {

    public static void main(String[] args) {
        Cook cook = new Cook("Amigo");
        Tablet tablet = new Tablet(1);
        tablet.addObserver(cook);
        
        Waiter waiter = new Waiter();  //4.5
        cook.addObserver(waiter);
        
        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();

        //15.3
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        
    }
    
}
