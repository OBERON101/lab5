/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5;


import ru.mephi.mortalkombatbLab5.characters.*;
import ru.mephi.mortalkombatbLab5.enums.PlayerChosenBoost;
import ru.mephi.mortalkombatbLab5.fabrics.EnemyFabric;
import ru.mephi.mortalkombatbLab5.utils.ResourcesUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * @author Мария
 */
public class CharacterAction {

    private final int[][] kind_fight = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};

    private final Player[] enemyes = new Player[6];

    EnemyFabric fabric = new EnemyFabric();

    private Player enemyy = null;

    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }

    public Player[] getEnemyes() {
        return this.enemyes;
    }

    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3,
                              Integer currentEnemy, Integer totalEnemyPerLocation,
                              Integer currentLocation, Integer locationCount) {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        String name = null;
        switch (i) {
            case 0 -> {
                enemyy = enemyes[0];
                icon1 = new ImageIcon(ResourcesUtils.getCharacterPicturesFolderPath("/Baraka.jpg"));
                name = "Baraka (танк)";
            }
            case 1 -> {
                enemyy = enemyes[1];
                icon1 = new ImageIcon(ResourcesUtils.getCharacterPicturesFolderPath("/Sub-Zero.jpg"));
                name = "Sub-Zero (маг)";
            }
            case 2 -> {
                enemyy = enemyes[2];
                icon1 = new ImageIcon(ResourcesUtils.getCharacterPicturesFolderPath("/Liu Kang.jpg"));
                name = "Liu Kang (боец)";
            }
            case 3 -> {
                enemyy = enemyes[3];
                icon1 = new ImageIcon(ResourcesUtils.getCharacterPicturesFolderPath("/Sonya Blade.jpg"));
                name = "Sonya Blade (солдат)";
            }
        }
        label2.setText("<html>" + name +
                "<br>Противник: " + currentEnemy + "/" + totalEnemyPerLocation +
                "<br>Локация: " + currentLocation + "/" + locationCount + "</html>");
        resizeImageIcon(label, icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(enemyy.getHealth() + "/" + enemyy.getMaxHealth());
        return enemyy;
    }

    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3,
                             int level, Integer currentEnemy, Integer totalEnemyPerLocation,
                             Integer currentLocation, Integer locationCount) {
        ImageIcon icon1 = new ImageIcon(ResourcesUtils.getCharacterPicturesFolderPath("/Shao Kahn.jpg"));
        label2.setText("<html>Shao Kahn (босс)" +
                "<br>Противник: " + currentEnemy + "/" + totalEnemyPerLocation +
                "<br>Локация: " + currentLocation + "/" + locationCount + "</html>");
        if (level <= 5) {
            enemyy = enemyes[4];
        } else {
            enemyy = enemyes[5];
        }
        resizeImageIcon(label, icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(enemyy.getHealth() + "/" + enemyy.getMaxHealth());
        return enemyy;
    }

    public int[] EnemyBehavior(int k1, int k2, int k3, double i) {
        int[] arr = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }

    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int[] arr = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, i);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, i);
        }
        return arr;
    }

    public void HP(Player player, JProgressBar progress) {

        progress.setValue(Math.max(player.getHealth(), 0));
    }

    public void AddPoints(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 0 -> human.increasePoints(25 + human.getHealth() / 4);
            case 1 -> human.increasePoints(30 + human.getHealth() / 4);
            case 2 -> human.increasePoints(35 + human.getHealth() / 4);
            case 3 -> human.increasePoints(45 + human.getHealth() / 4);
            case 4 -> human.increasePoints(55 + human.getHealth() / 4);
            default -> human.increasePoints(70 + human.getHealth() / 4);
        }
        human.increaseExperience(20);
        if (human.tryIncreaseLevel()) {
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }
    }

    public void AddPointsBoss(Human human, Player[] enemyes) {
        switch (human.getBossCount()) {
            case 1 -> {
                human.increaseExperience(30);
                human.increasePoints(45 + human.getHealth() / 2);
            }
            case 2 -> {
                human.increaseExperience(50);
                human.increasePoints(65 + human.getHealth() / 2);
            }
            case 3 -> {
                human.increaseExperience(60);
                human.increasePoints(90 + human.getHealth() / 2);
            }
            case 4 -> {
                human.increaseExperience(70);
                human.increasePoints(120 + human.getHealth() / 2);
            }
            case 5 -> {
                human.increaseExperience(80);
                human.increasePoints(170 + human.getHealth() / 2);
            }
            default -> {
                human.increaseExperience(100);
                human.increasePoints(200 + human.getHealth() / 2);
            }
        }
        if (human.tryIncreaseLevel()) {
            NewHealthHuman(human);
            for (int j = 0; j < 4; j++) {
                NewHealthEnemy(enemyes[j], human);
            }
        }
    }

    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void NewHealthHuman(Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1 -> {
                hp = 25;
                damage = 3;
            }
            case 2 -> {
                hp = 30;
                damage = 3;
            }
            case 3 -> {
                hp = 30;
                damage = 4;
            }
            case 4 -> {
                hp = 40;
                damage = 6;
            }
            default -> {
                hp = 50;
                damage = 8;
            }
        }
        PlayerChosenBoost playerChosenBoost = openDialog();
        human.increaseMaxHealth(hp * playerChosenBoost.getHealthBoost() * 25 / 10);
        human.increaseDamage(damage * playerChosenBoost.getDamageBoost() * 25 / 10);
    }

    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1 -> {
                hp = 32;
                damage = 25;
            }
            case 2 -> {
                hp = 30;
                damage = 20;
            }
            case 3 -> {
                hp = 23;
                damage = 24;
            }
            case 4 -> {
                hp = 25;
                damage = 26;
            }
            case 5 -> {
                hp = 27;
                damage = 26;
            }
            case 6 -> {
                hp = 28;
                damage = 27;
            }
            case 7 -> {
                hp = 30;
                damage = 29;
            }
            case 8 -> {
                hp = 32;
                damage = 32;
            }
            case 9 -> {
                hp = 35;
                damage = 36;
            }
            case 10 -> {
                hp = 38;
                damage = 37;
            }
            default -> {
                hp = 40;
                damage = 40;
            }
        }
        enemy.increaseMaxHealth(enemy.getMaxHealth() * hp / 100);
        enemy.increaseDamage(enemy.getDamage() * damage / 100);
        enemy.increaseLevel();
    }

    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1" -> {
                if (items[0].getCount() > 0) {
                    human.increaseHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
            }
            case "jRadioButton2" -> {
                if (items[1].getCount() > 0) {
                    human.increaseHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
            }
            case "jRadioButton3" -> {
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
            }
        }

        if (!dialog.isVisible()) {
            dialog1.dispose();
        }
    }

    private void resizeImageIcon(JLabel label, ImageIcon icon1) {
        label.setIcon(Optional.ofNullable(icon1).map(ImageIcon::getImage)
                .map(im -> im.getScaledInstance(189, 275, Image.SCALE_SMOOTH))
                .map(ImageIcon::new)
                .orElse(null));
        label.setBorder(BorderFactory.createEmptyBorder());
    }

    private PlayerChosenBoost openDialog() {
        PlayerChosenBoostWrapper playerChosenBoostWrapper = new PlayerChosenBoostWrapper();
        ChooseLevelUpBoost dialog = new ChooseLevelUpBoost(playerChosenBoostWrapper);
        dialog.pack();
        dialog.setVisible(true);
        return playerChosenBoostWrapper.getPlayerChosenBoost();
    }
}
