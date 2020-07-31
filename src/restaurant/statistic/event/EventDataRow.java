
package restaurant.statistic.event;

//11.4 - а данный момент он является интерфейсом-маркером, т.к. не содержит методов, и по нему мы определяем, является ли переданный объект событием или нет.

import java.util.Date;

public interface EventDataRow {
    public EventType getType();
    
    public Date getDate();  //15.4 - реализация которого вернет дату создания записи
    
    public int getTime();   //15.4 - реализация которого вернет время - продолжительность
    
}
