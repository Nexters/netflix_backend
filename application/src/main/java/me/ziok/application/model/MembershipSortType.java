package me.ziok.application.model;

public enum MembershipSortType {

    basic(0), standard(1), premium(2);

    private int value;

    MembershipSortType(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }

    public static MembershipSortType value(int value){
        switch (value){
            case 0 :
                return basic;
            case 1 :
                return standard;
            case 2 :
                return premium;
            default :
                return premium;
        }
    }
}
