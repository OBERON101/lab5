/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5.fabrics;

import ru.mephi.mortalkombatbLab5.characters.Player;
import ru.mephi.mortalkombatbLab5.characters.ShaoKahn;

/**
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        if (i == 0) {
            return new ShaoKahn(3, 100, 30, 1);
        } else {
            return new ShaoKahn(3, 145, 44, 1);
        }
    }
}
