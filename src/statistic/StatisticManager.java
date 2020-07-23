
package statistic;

//11.1 - StatisticManager - С его помощью будем регистрировать события в хранилище. У нас должно быть одно хранилище с одной точкой входа. Поэтому сделаем StatisticManager синглтоном.

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kitchen.Cook;
import statistic.event.EventDataRow;
import statistic.event.EventType;

public class StatisticManager {
    private static StatisticManager instance;  // приватное статическое поле, содержащее одиночный объект
    private StatisticStorage statisticStorage = new StatisticStorage();   //13.2
    private Set<Cook> cooks = new HashSet<>();   //15.5 - множество поваров
    
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
        statisticStorage.put(data);   //14.6 - регистрируем полученное событие в statisticStorage.
    }
    
    //15.5 - который зарегистрирует полученного повара.
    public void register(Cook cook){
        cooks.add(cook);
    }
    
    //13 - Хранилище
    private class StatisticStorage{
        Map <EventType, List<EventDataRow>> storage = new HashMap<>();    //13.3
        
        public StatisticStorage(){
            for(EventType e: EventType.values()){
                storage.put(e, new ArrayList<EventDataRow>());  //13.4
            }
        }
        
        //14.1
        private void put(EventDataRow data){
            
        }
        
        //16.2 - возвращает события в зависимости от типа
        public List<EventDataRow> getStorage(EventType eventType){
            return storage.get(eventType);
        }
    }
    
    //16.2 - который из хранилища достанет все данные, относящиеся к отображению рекламы, и посчитает общую прибыль за каждый день.
    public TreeMap<Date, Long> getAdvertisingProfitByDay(){
        TreeMap<Date, Long> result = new TreeMap<>();
        List<EventDataRow> list = statisticStorage.getStorage(EventType.SELECTED_VIDEOS);
        for(EventDataRow ev: list){
            //суммируем по датам
            if (ev.getDate() != null)
                result.put(ev.getDate(), result.get(ev.getDate()) + Long.valueOf(ev.getTime()));
            else
                result.put(ev.getDate(), Long.valueOf(ev.getTime()));
        }
        
        return result;
    }
    
}
