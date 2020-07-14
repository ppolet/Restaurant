
package kitchen;

//1.5
public enum Dish {
    Fish, Steak, Soup, Juice, Water;
    
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
