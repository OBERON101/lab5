/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.fabrics;


import ru.mephi.mortalkombatbLab5.characters.LiuKang;
import ru.mephi.mortalkombatbLab5.characters.Player;

/**
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        return new LiuKang(1, 70, 20, 1);
    }
}
