
package restaurant.ad;

//17 - который будет предоставлять информацию из AdvertisementStorage в нужном нам виде. Синглтон.

import java.util.ArrayList;
import java.util.List;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;  // приватное статическое поле, содержащее одиночный объект
    
    private AdvertisementStorage advStorage = AdvertisementStorage.getInstance();   //17.2
            
    
    // конструктор класса (конструктор по-умолчанию) приватным (чтобы доступ к нему был закрыт за пределами класса, тогда он не сможет возвращать новые объекты)
    private StatisticAdvertisementManager(){
        
    }

    // статический создающий метод, который будет использоваться для получения одиночки
    public static StatisticAdvertisementManager getInstance(){
        if(instance == null){
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }
    
    //17.3 - которые из хранилища AdvertisementStorage достанут все необходимые данные - соответственно список активных рекламных роликов.
    public List<Advertisement> getActiveAdvVideo(){
        List<Advertisement> list = new ArrayList<>();
        for(Advertisement adv: advStorage.list()){
            if(adv.getHits() > 0) list.add(adv);
        }
        return list;
    }

    //17.3 - которые из хранилища AdvertisementStorage достанут все необходимые данные - соответственно список НЕактивных рекламных роликов.
    public List<Advertisement> getNonActiveAdvVideo(){
        List<Advertisement> list = new ArrayList<>();
        for(Advertisement adv: advStorage.list()){
            if(adv.getHits() == 0) list.add(adv);
        }
        return list;
    }
    
}
