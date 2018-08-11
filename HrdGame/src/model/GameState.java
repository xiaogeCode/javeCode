package model;

import util.CommStringInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: GameState
 * Author:   xiaoge
 * Date:     2018/8/7 14:09
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class GameState implements CommStringInterface{
    int[][] map;
    List<Warrior> heros;
    MoveAction move;
    GameState parrent;

    public GameState() {
        map = new int[HRD_WIDTH][HRD_HEIGHT];
        this.parrent = null;
        this.move = null;
        heros = new ArrayList<Warrior>();
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public List<Warrior> getHeros() {
        return heros;
    }

    public void setHeros(List<Warrior> heros) {
        /*for (Warrior hero:heros) {
            this.heros.add(hero);
        }*/
        this.heros = heros;
    }

    public MoveAction getMove() {
        return move;
    }

    public void setMove(MoveAction move) {
        this.move = move;
    }

    public GameState getParrent() {
        return parrent;
    }

    public void setParrent(GameState parrent) {
        this.parrent = parrent;
    }


}
