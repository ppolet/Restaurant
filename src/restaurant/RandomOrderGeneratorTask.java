
package restaurant;

//18.2 - создадим несколько планшетов, которые будут рандомно генерировать заказы

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> listTablets;   //18.2.2
    
    public RandomOrderGeneratorTask(List<Tablet> listTablets){
        this.listTablets = listTablets;
    }

    @Override
    public void run() {
        Tablet tablet = listTablets.get((int)Math.random()*listTablets.size());  //18.2.2
        
    }
    
}
