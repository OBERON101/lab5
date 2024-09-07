package ru.mephi.mortalkombatbLab5.fabrics;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import ru.mephi.mortalkombatbLab5.characters.Player;

/**
 * @author Мария
 */
public class EnemyFabric {

    public Player create(int i, int j) {
        EnemyFabricInterface fabric = switch (i) {
            case 0 -> new BarakaFabric();
            case 1 -> new SubZeroFabric();
            case 2 -> new LiuKangFabric();
            case 3 -> new SonyaBladeFabric();
            case 4 -> new ShaoKahnFabric();
            default -> null;
        };

        assert fabric != null;
        return fabric.create(j);
    }
}
