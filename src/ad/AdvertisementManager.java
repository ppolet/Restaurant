
package ad;

//6.4 - у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа.

import restaurant.ConsoleHelper;

// Он также будет взаимодействовать с плеером и отображать ролики.
public class AdvertisementManager {
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();  //7.4
    private int timeSeconds;
    
    public AdvertisementManager(int timeSeconds){
        this.timeSeconds = timeSeconds;   //8.3
    }
    
    public void processVideos(){
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
