
package restaurant.ad;

//6.4 - хранилище рекламных роликов. СИНГЛТОН

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance;  // приватное статическое поле, содержащее одиночный объект

    private final List<Advertisement> videos = new ArrayList<>();  //7.1
    
    // конструктор класса (конструктор по-умолчанию) приватным (чтобы доступ к нему был закрыть за пределами класса, тогда он не сможет возвращать новые объекты)
    private AdvertisementStorage(){
        //7.3
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));   //10 min
    }  
    
    // статический создающий метод, который будет использоваться для получения одиночки
    public static AdvertisementStorage getInstance(){
        if(instance == null){
            instance = new AdvertisementStorage();
        }
        return instance;
    }
    
    
    //7.2 - вернет список всех существующих доступных видео.
    public List<Advertisement> list(){
        return videos;
    }
    
    //7.3 - добавит новое видео в список videos.
    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
    
}
