/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5;

/**
 * @author Мария
 */
public class Result {

    private final String name;
    private final int points;

    public Result(String n, int p) {
        this.name = n;
        this.points = p;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

}
