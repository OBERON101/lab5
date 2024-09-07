/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5;

//ADD IMAGE!!!

import ru.mephi.mortalkombatbLab5.characters.Human;
import ru.mephi.mortalkombatbLab5.characters.Player;
import ru.mephi.mortalkombatbLab5.characters.ShaoKahn;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Мария
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int[] kind_attack = {0};
    int i = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;

    private Integer locationsCount;
    private Map<Integer, Integer> enemiesPerLocationMap = new HashMap<>();
    private Integer currentLocation;

    public Fight() {
        constructMap();
        this.currentLocation = 1;
    }

    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {
        if (stun == 1) {
            p1.setAttack(-1);
        }
        if (Math.random() < 0.2 && p2 instanceof ShaoKahn) {
            switch (p1.getAttack()) {
                case 0 -> {
                    p2.increaseHealth((p2.getMaxHealth() - p2.getHealth()) * 5 / 10);
                    l.setText(p2.getName() + " healed");
                    l2.setText(p1.getName() + " didn't attacked");
                }
                case 1 -> {
                    p2.increaseHealth(-p1.getDamage() * 2);
                    l.setText(p2.getName() + " failed healing");
                    l2.setText(p1.getName() + " attacked");
                }
                case -1 -> {
                    l2.setText(p1.getName() + " was stunned");
                    stun = 0;
                    p2.increaseHealth((p2.getMaxHealth() - p2.getHealth()) * 5 / 10);
                    l.setText(p2.getName() + " healed");
                }
            }
        }
        switch (p1.getAttack() + Integer.toString(p2.getAttack())) {
            case "10" -> {
                v = Math.random();
                if (p1 instanceof ShaoKahn & v < 0.15) {
                    p2.increaseHealth(-(int) (p1.getDamage() * 0.5));
                    l2.setText("Your block is broken");
                } else {
                    p1.increaseHealth(-(int) (p2.getDamage() * 0.5));
                    l2.setText(p2.getName() + " counterattacked");
                }
            }
            case "11" -> {
                p2.increaseHealth(-p1.getDamage());
                l2.setText(p1.getName() + " attacked");
            }
            case "00" -> {
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                l2.setText("Both defended themselves");
            }
            case "01" -> l2.setText(p1.getName() + " didn't attacked");
            case "-10" -> {
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " didn't attacked");
            }
            case "-11" -> {
                p1.increaseHealth(-p2.getDamage());
                l.setText(p1.getName() + " was stunned");
                stun = 0;
                l2.setText(p2.getName() + " attacked");
            }
        }
    }

    public void Hit(Human human, Player enemy, int a, JLabel label,
                    JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
                    JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
                    JDialog dialog2, JFrame frame, ArrayList<Result> results,
                    JLabel label4, JLabel label5, JLabel label6, JLabel label7,
                    JLabel label8, Items[] items, JRadioButton rb) {
        label7.setText("");
        human.setAttack(a);

        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action);
            k = 0;
        }
        enemy.setAttack(kind_attack[k]);
        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (human.getCurrentWinPerLocation() + 1 > enemiesPerLocationMap.get(currentLocation)
                    && Objects.equals(currentLocation, locationsCount)) {
                EndFinalRound(human, action, results, dialog1, dialog2,
                        frame, label4, label5);
            } else {
                EndRound(human, enemy, dialog, label3, action, items);
            }
        }
    }

    public void EndRound(Human human, Player enemy, JDialog dialog, JLabel label,
                         CharacterAction action, Items[] items) {

        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        if (human.getHealth() > 0) {
            human.increaseWin();
            human.increaseCurrentWinPerLocation();
            if (enemy instanceof ShaoKahn) {
                label.setText("You win boss. Next location");
                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(human, action.getEnemyes());
                this.currentLocation++;
                human.setCurrentWinPerLocation(0);
            } else {
                label.setText("You win");
                action.AddItems(25, 15, 5, items);
                action.AddPoints(human, action.getEnemyes());
            }
        } else {
            label.setText(enemy.getName() + " win");
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    public void EndFinalRound(Human human, CharacterAction action,
                              ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
                              JLabel label1, JLabel label2) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.increaseWin();
            human.increaseCurrentWinPerLocation();
            action.AddPoints(human, action.getEnemyes());
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (Result result : results) {
                if (human.getPoints() < result.getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] ResetAttack() {
        return new int[]{0};
    }

    public Player NewRound(Human human, JLabel label, JProgressBar pr1,
                           JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action) {

        Player enemy1;
        if (human.getCurrentWinPerLocation() + 1 == enemiesPerLocationMap.get(currentLocation)) {
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel(),
                    human.getCurrentWinPerLocation() + 1,
                    enemiesPerLocationMap.get(currentLocation),
                    currentLocation, locationsCount);
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3,
                    human.getCurrentWinPerLocation() + 1,
                    enemiesPerLocationMap.get(currentLocation),
                    currentLocation, locationsCount);
        }
//        if (human.getWin() == 6 | human.getWin() == 11) {
//            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
//        } else {
//            enemy1 = action.ChooseEnemy(label, label2, text, label3);
//        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setHealth(human.getMaxHealth());
        enemy1.setHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);
        return enemy1;
    }

    public void setLocationsCount(Integer locationsCount) {
        this.locationsCount = locationsCount;
        for (int i = 0; i < locationsCount; i++) {
            enemiesPerLocationMap.put(i + 1, new Random().nextInt(3, 11));
        }
    }

    private void constructMap() {
        this.enemiesPerLocationMap = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15})
                .collect(Collectors.toMap(i -> i, i -> switch (i) {
                    case 1 -> new Random().nextInt(1, 5);
                    case 2 -> new Random().nextInt(2, 5);
                    case 3 -> new Random().nextInt(3, 5);
                    case 4 -> new Random().nextInt(3, 6);
                    case 5, 6 -> new Random().nextInt(4, 7);
                    case 7 -> new Random().nextInt(5, 8);
                    case 8, 9 -> new Random().nextInt(6, 9);
                    case 10, 11 -> new Random().nextInt(7, 10);
                    case 12 -> new Random().nextInt(8, 11);
                    case 13 -> new Random().nextInt(8, 12);
                    case 14 -> new Random().nextInt(9, 12);
                    case 15 -> new Random().nextInt(9, 13);
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                }));
    }

    public Integer getLocationsCount() {
        return locationsCount;
    }

    public Integer getCurrentLocation() {
        return currentLocation;
    }

    public Integer getTotalEnemiesPerCurrentLocation() {
        return enemiesPerLocationMap.get(currentLocation);
    }
}
