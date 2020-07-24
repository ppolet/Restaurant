
package ad;

//17 - который будет предоставлять информацию из AdvertisementStorage в нужном нам виде. Синглтон.
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;  // приватное статическое поле, содержащее одиночный объект
    
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
    
}
