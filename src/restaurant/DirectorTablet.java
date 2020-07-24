
package restaurant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import statistic.StatisticManager;

//15 - планшет для директора
public class DirectorTablet {
    
    // 1) какую сумму заработали на рекламе, сгруппировать по дням;
    public void printAdvertisementProfit(){
        TreeMap<Date, Long> advertisingByDay = StatisticManager.getInstance().getAdvertisingProfitByDay();
        if (advertisingByDay.isEmpty()) {
            ConsoleHelper.writeMessage("---- No statistic at advertising vide ----");
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
    public void printCookWorkloading(){
        
    }
    
    // 3) список активных роликов и оставшееся количество показов по каждому;
    public void printActiveVideoSet(){
        
    }
    
    // 4) список неактивных роликов (с оставшемся количеством показов равным нулю).
    public void printArchivedVideoSet(){
        
    }
    
}
