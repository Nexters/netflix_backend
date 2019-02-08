package me.ziok.application.model;

import lombok.Getter;
import lombok.Setter;

public enum PostSortType {

    //최신순, 남은인원 순, 저가순
    recent(0), recruitNumber(1), lowFee(2);

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
                return recruitNumber;
            case 2:
                return lowFee;
            default:
                return recent;
        }
    }
}