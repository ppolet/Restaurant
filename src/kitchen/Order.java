
package kitchen;

//1.4 - В классе Order (заказ) должна быть информация, относящаяся к списку выбранных пользователем блюд.

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import restaurant.ConsoleHelper;
import restaurant.Tablet;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;
    
    public Order(Tablet tablet) throws IOException{
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
    
    //2.4
    @Override
    public String toString(){
        if (dishes.isEmpty()) return "";
        
        StringBuilder st = new StringBuilder();
        st.append("Your order: ");
        st.append(dishes.toString());
        st.append(" of " + tablet.toString());
        
//        for(int i = 0; i<dishes.size(); i++){
//            st.append(dishes.get(i));
//            if (i != dishes.size()-1) st.append(", ");
//        }
        return st.toString();
    }
    
    //5.2.3
    public int getTotalCookingTime(){
        int result = 0;
        for(Dish d: dishes){
            result += d.getDuration();
        }
        return result;
    }
    
    //5.3 - который будет определять, есть ли какие либо блюда в заказе.
    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    
    public Tablet getTablet(){
        return tablet;
    }
    
}
