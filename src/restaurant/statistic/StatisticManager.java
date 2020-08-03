
package restaurant.statistic;

//11.1 - StatisticManager - С его помощью будем регистрировать события в хранилище. У нас должно быть одно хранилище с одной точкой входа. Поэтому сделаем StatisticManager синглтоном.

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import restaurant.kitchen.Cook;
import restaurant.statistic.event.CookedOrderEventDataRow;
import restaurant.statistic.event.EventDataRow;
import restaurant.statistic.event.EventType;
import restaurant.statistic.event.VideoSelectedEventDataRow;

public class StatisticManager {
    private static StatisticManager instance;  // приватное статическое поле, содержащее одиночный объект
    private StatisticStorage statisticStorage = new StatisticStorage();   //13.2
    //private Set<Cook> cooks = new HashSet<>();   //15.5 - множество поваров
    
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
    
    //21.4 - список всех поваров
//    public Set<Cook> getCooks(){
//        return cooks;
//    }
    
    //11.5 - который будет регистрировать событие в хранилище.
    public void register(EventDataRow data){
        statisticStorage.put(data);   //14.6 - регистрируем полученное событие в statisticStorage.
    }
    
    //15.5 - который зарегистрирует полученного повара.
//    public void register(Cook cook){
//        cooks.add(cook);
//    }
    
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
    // возвращает отсортированный map <Data, Сумма>
    public TreeMap<Date, Long> getAdvertisingProfitByDay(){
        TreeMap<Date, Long> result = new TreeMap<>(Comparator.reverseOrder());    //сортировка дат в убывающем порядке
        List<EventDataRow> list = statisticStorage.getStorage(EventType.SELECTED_VIDEOS);
        GregorianCalendar cal = new GregorianCalendar();
        for(EventDataRow ev: list){
            //суммируем по датам
            VideoSelectedEventDataRow temp = (VideoSelectedEventDataRow)ev;
            cal.setTime(temp.getDate());
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  //преобразуем дату в дату без времени (00:00:00)
            if (result.containsKey(cal.getTime()))
                result.put(cal.getTime(), result.get(cal.getTime()) + temp.getAmount());  //если уже есть такая дата в мапе
            else
                result.put(cal.getTime(), temp.getAmount());  //если такой даты в мапе нет
        }
        return result;
    }
    
    
    //16.4 - загрузка (рабочее время) повара, сгруппировать по дням. Метод, который из хранилища достанет все данные, относящиеся к работе повара, 
    //       и посчитает общую продолжительность работы для каждого повара отдельно. <Дата, <Повар, ПродолжительностьРаботы>>
    public TreeMap<Date, TreeMap<String, Integer>> getCookWorkLoadingByDay(){
        TreeMap<Date, TreeMap<String, Integer>> result = new TreeMap<>(Comparator.reverseOrder());   //сортировка дат в убывающем порядке
        List<EventDataRow> list = statisticStorage.getStorage(EventType.COOKED_ORDER);
        GregorianCalendar cal = new GregorianCalendar();
        for(EventDataRow ev: list){
            //суммируем по датам
            CookedOrderEventDataRow temp = (CookedOrderEventDataRow)ev;
            cal.setTime(temp.getDate());
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  //преобразуем дату в дату без времени (00:00:00)
            TreeMap<String, Integer> cookTimeWork = new TreeMap<>();
            int tempTimeWork = 0;
            if (result.containsKey(cal.getTime())){   //такая дата уже есть, значит есть и внутренний мап
                cookTimeWork = result.get(cal.getTime());
                if(cookTimeWork.containsKey(temp.getCookName())){   //уже есть такое имя повара, складываем время работы
                    tempTimeWork = cookTimeWork.get(temp.getCookName());   //время работы, которое уже есть в мапе
                }
            }
            cookTimeWork.put(temp.getCookName(), tempTimeWork + temp.getTime());  // складываем время работы. которое уже было в мапе и новое значение для этого повара
            result.put(cal.getTime(), cookTimeWork);
        }
        return result;
    }
    
    
}
