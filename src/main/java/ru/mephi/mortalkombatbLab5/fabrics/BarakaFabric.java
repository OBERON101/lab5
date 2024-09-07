/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.fabrics;


import ru.mephi.mortalkombatbLab5.characters.Baraka;
import ru.mephi.mortalkombatbLab5.characters.Player;

/**
 * @author Мария
 */
public class BarakaFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        return new Baraka(1, 100, 12, 1);
    }
}
