
package restaurant;

import ad.Advertisement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import statistic.StatisticManager;
import ad.StatisticAdvertisementManager;

//15 - планшет для директора
public class DirectorTablet {
    
    // 1) какую сумму заработали на рекламе, сгруппировать по дням;
    public void printAdvertisementProfit(){
        TreeMap<Date, Long> advertisingByDay = StatisticManager.getInstance().getAdvertisingProfitByDay();
        if (advertisingByDay.isEmpty()) {
            ConsoleHelper.writeMessage("---- No statistic about advertising video ----");
            return;
        }
        
        long totalSum = 0;  //общая сумма всех заказов за все время
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy");
        for(Map.Entry<Date, Long> pair: advertisingByDay.entrySet()){
            ConsoleHelper.writeMessage(dateFormat.format(pair.getKey()) + " - " + pair.getValue());    //16.3
            totalSum += pair.getValue();
        }
        ConsoleHelper.writeMessage("Total - " + totalSum);
    }
    
    // 2) загрузка (рабочее время) повара, сгруппировать по дням;
    //16.5 - вывести в консоль в убывающем порядке даты, имена поваров и время работы в минутах (округлить в большую сторону)
    public void printCookWorkloading(){
        TreeMap<Date, TreeMap<String, Integer>> cookWorkLoading = StatisticManager.getInstance().getCookWorkLoadingByDay();
        if (cookWorkLoading.isEmpty()) {
            ConsoleHelper.writeMessage("---- No statistic about cook work load ----");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy");
        for(Map.Entry<Date, TreeMap<String, Integer>> pair: cookWorkLoading.entrySet()){
            System.out.println(dateFormat.format(pair.getKey()));  //выводим дату
            for(Map.Entry<String, Integer> innerPair: pair.getValue().entrySet()){
                System.out.println(innerPair.getKey() + " - " + innerPair.getValue() + " min"); // имя повара - время затраченное на приготовление заказов за день
            }
            System.out.println();
        }
    }
    
    // 3) список активных роликов и оставшееся количество показов по каждому;
    //17.4
    public void printActiveVideoSet(){
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getActiveAdvVideo();
        Collections.sort(list, new Comparator<Advertisement>(){
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = o1.getName().compareTo(o2.getName());
                if(result == 0){
                    result = (o1.getHits() < o2.getHits()) ? -1: 1;
                }
                return result;
            }
        });
        for(Advertisement v: list){
            System.out.println(v.getName() + " - " + v.getHits());
        }
        
    }
    
    // 4) список неактивных роликов (с оставшемся количеством показов равным нулю).
    //17.4
    public void printArchivedVideoSet(){
        List<Advertisement> list = StatisticAdvertisementManager.getInstance().getNonActiveAdvVideo();
        Collections.sort(list, new Comparator<Advertisement>(){
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Advertisement v: list){
            System.out.println(v.getName());
        }
    }
    
}
