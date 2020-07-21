
package statistic;

//11.1 - StatisticManager - С его помощью будем регистрировать события в хранилище. У нас должно быть одно хранилище с одной точкой входа. Поэтому сделаем StatisticManager синглтоном.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import statistic.event.EventDataRow;
import statistic.event.EventType;

public class StatisticManager {
    private static StatisticManager instance;  // приватное статическое поле, содержащее одиночный объект
    
    // конструктор класса (конструктор по-умолчанию) приватным (чтобы доступ к нему был закрыт за пределами класса, тогда он не сможет возвращать новые объекты)
    private StatisticManager(){
        
    }
    
    // статический создающий метод, который будет использоваться для получения одиночки
    public static StatisticManager getInstance(){
        if(instance == null){
            instance = new StatisticManager();
        }
        return instance;
    }
    
    //11.5 - который будет регистрировать событие в хранилище.
    public void register(EventDataRow data){

    }
    
    //13 - Хранилище
    private class StatisticStorage{
        private StatisticStorage statisticStorage = new StatisticStorage();
        Map <EventType, List<EventDataRow>> storage = new HashMap<>();    //13.3
        
        public StatisticStorage(){
            for(EventType e: EventType.values()){
                storage.put(e, new ArrayList<EventDataRow>());  //13.4
            }
        }
        
        //14.1
        private void put(EventDataRow data){
            
        }
    }
    
}
