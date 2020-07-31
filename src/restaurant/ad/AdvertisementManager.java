
package restaurant.ad;

//6.4 - у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов и их последовательность для каждого заказа.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import restaurant.ConsoleHelper;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.VideoSelectedEventDataRow;

// Он также будет взаимодействовать с плеером и отображать ролики.
public class AdvertisementManager{
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();  //7.4
    private int timeSeconds;
    
    public AdvertisementManager(int timeSeconds){
        this.timeSeconds = timeSeconds;   //8.3
    }
    
    private List<Advertisement> bestAdvList = new ArrayList<>();  //отобранные ролики с максимальной суммарной ценой и временем меньше времени приготовления
    private long maxAmount = 0;   // максимальная цена роликов
    private long durationBestAdvList = 0;   // продолжительность отобранных роликов с максимальной ценой
    
    public void processVideos() throws NoVideoAvailableException{
        //10
        findBestAdvertisement(storage.list());
        if (bestAdvList.isEmpty()) throw new NoVideoAvailableException();   // 9.2.3 нет рекламных видео, которые можно показать посетителю
        /*
            2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа одного рекламного ролика
            в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки
            Используйте метод Collections.sort    
        */
        Collections.sort(bestAdvList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long c = o1.getAmountPerOneDisplaying() - o2.getAmountPerOneDisplaying();
                if (c == 0){
                    c = o1.getAmountPerOneDisplaying()/o1.getDuration() - o2.getAmountPerOneDisplaying()/o2.getDuration();
                    if (c == 0) return 0;
                }
                return (c < 0)? -1: 1;
            }
        });
        
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestAdvList, maxAmount, timeSeconds));   //14.5 - Зарегистрируй событие "видео выбрано" перед отображением рекламы пользователю.
        
        for(Advertisement a: bestAdvList){
            a.revalidate();    // 10 - Для каждого показанного видео-ролика должен быть вызван метод revalidate().
        }
    }
    
    
    //Полный перебор с рекурсией. Поиск оптимального кол-ва роликов с максимальной ценой
    //Список роликов в bestAdvList; максимальная цена роликов - maxAmount; продолжительность роликов - durationBestAdvList
    //.....Оптимальнее применить динамическое программирование (задача о рюкзаке).....
    private void findBestAdvertisement(List<Advertisement> list){
        if (list.size()>0){
            int tempSumDuration = 0;
            int tempSumPrice = 0;
            //считаем сумму и время роликов
            for(int i = 0; i<list.size(); i++){
                tempSumDuration += list.get(i).getDuration();
                if (tempSumDuration <= timeSeconds){
                    tempSumPrice += list.get(i).getAmountPerOneDisplaying();
                } else {  //// перебор
                    tempSumPrice = -1;
                    break;
                }
            }
            /*
                4) если существует несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
                4.1) выбрать тот вариант, у которого суммарное время максимальное;
                4.2) если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов;            
            */            
            // цена предметов больше максимальной, новый лучший массив роликов
            if(tempSumPrice > maxAmount){
                bestAdvList.addAll(list);
                maxAmount = tempSumPrice;
                durationBestAdvList = tempSumDuration;
            } else if (tempSumPrice == maxAmount){   //10.4
                if (tempSumDuration > durationBestAdvList){   //10.4.1
                    bestAdvList.addAll(list);
                    durationBestAdvList = tempSumDuration;
                } else if (tempSumDuration == durationBestAdvList){   //10.4.2
                    if (list.size() < bestAdvList.size()){
                        bestAdvList.addAll(list);
                    }
                }
            }
        }
        
        for(int i = 0; i<list.size(); i++){
            list.remove(i);
            findBestAdvertisement(list);   // рекурсия
        }
    }
    
}



