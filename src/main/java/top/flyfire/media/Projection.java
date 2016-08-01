package top.flyfire.media;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyy_work on 2016/8/1.
 */
public class Projection {

    private int value;

    private List<Integer> ingredient;

    public Projection() {
        this.value = 0;
        this.ingredient = new ArrayList<Integer>();
    }

    public Projection putIngredient(int pos){
        this.ingredient.add(pos);
        this.value++;
        return this;
    }

    public int getValue(){
        return this.value;
    }


}
