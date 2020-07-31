
package restaurant.kitchen;

//3.1 - он будет готовить

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.ConsoleHelper;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.CookedOrderEventDataRow;


public class Cook extends Observable {
    private String name;
    private boolean busy;   //21.1
    
    public Cook(String name){
        this.name = name;
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
    
}
