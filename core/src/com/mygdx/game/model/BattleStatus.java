package com.mygdx.game.model;

/**
 * Created by Sash on 03.05.2018.
 */

public class BattleStatus { //класс необходимый для распределения игроков по боям
    Integer number;
    String status;
    Integer queueSize;//размер очереди
    public BattleStatus()
    {

    }
    public BattleStatus(Integer number,Integer queueSize,String status)
    {
        this.number=number;
        this.queueSize=queueSize;
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
        this.setQueueSize(battleStatus.getQueueSize());
        this.setNumber(battleStatus.getNumber());
    }

    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }
}
