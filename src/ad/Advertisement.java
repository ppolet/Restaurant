
package ad;

//6 - Рекламное объявление
public class Advertisement {
    private Object content;     // видео
    private String name;        // имя/название
    private long initialAmount; // начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;           // количество оплаченных показов
    private int duration;       // продолжительность в секундах
    private long amountPerOneDisplaying;    //8.1 - Оно должно равняться стоимости одного показа рекламного объявления в копейках (initialAmount/hits).

    //6.3
    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount/hits;   //8.1 - Оно должно равняться стоимости одного показа рекламного объявления в копейках (initialAmount/hits).
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits(){
        return hits;
    }
    
    //9.2.5
    public void revalidate(){
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        } else hits--;
        
    }
    
}
