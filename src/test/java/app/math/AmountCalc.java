package app.math;


import java.util.ArrayList;
import java.util.List;

public class AmountCalc {

    private double price;

    public float getPrice() {
        return (float) price;
    }


    public void countPrice(int myAmount){
        double lastElement = 0;
        List<Double> myList = new ArrayList();
        myList.add(0,0.0);
        for (int i = 1; i <= myAmount; i++){
            if(i <= 5){
                myList.add(i,0.5);
            } else if (i <= 10){
                myList.add(i,0.4);
            } else if (i <= 20){
                myList.add(i,0.3);
            } else if (i <= 40){
                myList.add(i,0.2);
            } else if (i <= 60){
                myList.add(i,0.15);
            } else {
                lastElement = myAmount - 60;
                lastElement = lastElement * 0.1;
                myList.add(61,lastElement);
                break;
            }
        }

        for (int i = 0; i < myList.size(); i++){
            this.price = this.price + myList.get(i);
        }
    }

}
