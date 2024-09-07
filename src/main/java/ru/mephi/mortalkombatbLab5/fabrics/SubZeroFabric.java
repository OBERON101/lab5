/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.fabrics;


import ru.mephi.mortalkombatbLab5.characters.Player;
import ru.mephi.mortalkombatbLab5.characters.SubZero;

/**
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        return new SubZero(1, 60, 16, 1);
    }

}
