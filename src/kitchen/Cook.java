
package kitchen;

//3.1 - он будет готовить

import java.util.Observable;
import java.util.Observer;
import restaurant.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;


public class Cook extends Observable implements Observer {
    private String name;
    
    public Cook(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cook {" + "name = " + name + '}';
    }

    /*
    -observable - объект, который отправил нам значение
    -arg - само значение, в нашем случае - это объект Order    
    */
    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time: " + order.getTotalCookingTime() + " min.");   //3.3
        //14.4 - Зарегистрируй событие для повара во время приготовления еды.
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes())); 
        setChanged();           //4.4
        notifyObservers(order);  //4.4
    }
    
    
}
