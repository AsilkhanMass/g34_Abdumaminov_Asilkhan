package uz.pdp.enums;

public enum MonthEnum {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULE(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int index;
    private final int numberOfDays;

    MonthEnum(int index, int numberOfDays) {
        this.index = index;
        this.numberOfDays = numberOfDays;
    }
    public static int numOfDay(int index){
        MonthEnum[] monthEnums = MonthEnum.values();
        for(MonthEnum monthEnum: monthEnums){
            if(monthEnum.index==index){
                return monthEnum.numberOfDays;
            }
        }
        return 30;
    }
}
