package com.mygdx.game.model;

/**
 * Created by Sash on 03.05.2018.
 */

public class BattleStatus { //класс необходимый для распределения игроков по боям
    Integer number;
    String status;
    public BattleStatus()
    {

    }
    public BattleStatus(Integer number,String status)
    {
        this.number=number;
        this.status=status;
    }
    public BattleStatus(BattleStatus battleStatus)
    {
        this.status=battleStatus.getStatus();
        this.number=battleStatus.getNumber();
    }

    public void setNumber(Integer battleNumber) {
        this.number = battleNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }
    public void setBattleStatus(BattleStatus battleStatus)
    {
        this.setStatus(battleStatus.getStatus());
        this.setNumber(battleStatus.getNumber());
    }
}
