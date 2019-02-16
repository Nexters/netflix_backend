package me.ziok.application.model;

import lombok.Getter;
import lombok.Setter;

public enum PostSortType {

    //최신순, 저가순
    recent(0), lowFee(1);

    private int value;
    PostSortType(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
    public static PostSortType value(int value){
        switch (value){
            case 0 :
                return recent;
            case 1:
               return lowFee;
            default:
                return recent;
        }
    }
}