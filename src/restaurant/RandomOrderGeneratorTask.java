
package restaurant;

//18.2 - создадим несколько планшетов, которые будут рандомно генерировать заказы

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> tablets;   //18.2.2
    private int interval; //19
    
    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval){
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) return;
        
        Tablet tablet = tablets.get((int)Math.random()*tablets.size());  //18.2.2
        
    }
    
}
