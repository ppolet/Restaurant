
package statistic.event;

//11.4 - а данный момент он является интерфейсом-маркером, т.к. не содержит методов, и по нему мы определяем, является ли переданный объект событием или нет.
public interface EventDataRow {
    public EventType getType();
}
