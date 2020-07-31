
package restaurant.kitchen;

//1.5
public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);   //5.1.1
    
    private int duration;
    
    private Dish(int duration){
        this.duration = duration;
    }
    
    //5.1.2
    public int getDuration(){
        return duration;
    }
    
    //1.6 - сформирует строку из всех блюд
    public static String allDishesToString(){
        StringBuilder s = new StringBuilder();
        Dish[] dish = Dish.values();
        for(Dish d:dish){
            s.append(d.toString());
            if (d.ordinal() != dish.length-1) s.append(", ");
        }
        return s.toString();
    }
}
