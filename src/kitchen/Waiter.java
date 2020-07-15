package kitchen;

//4 - (Официант), он будет относить заказы назад к столику. Официант будет безымянным.

import java.util.Observable;
import java.util.Observer;
import restaurant.ConsoleHelper;

public class Waiter implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg + " was cooked by " + o.toString());  //4.3
    }
    
}
