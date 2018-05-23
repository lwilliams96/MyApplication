package com.example.treyn.myapplication;

public class Calories {
    int gain, loss, total;

    public void setGain(int gain){
        this.gain = (this.gain + gain);
    }

    public int getGain(){
        return this.gain;
    }

    public void setLoss(int loss){
        this.loss = (this.loss + loss);
    }

    public int getLoss(){
        return this.loss;
    }

    public void setTotal(int gain, int loss){
        int curTotal = (gain - loss);
        this.total = (this.total + curTotal);
    }

    public int getTotal(){
        return this.total;
    }


}
