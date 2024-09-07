/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.mephi.mortalkombatbLab5;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.mephi.mortalkombatbLab5.characters.Human;
import ru.mephi.mortalkombatbLab5.characters.Player;
import ru.mephi.mortalkombatbLab5.utils.ResourcesUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Мария
 */
public class Game {
    private final String filePath = ResourcesUtils.getScoreTablePath();

    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight;
    private final ArrayList<Result> results = new ArrayList<>();

    public Game() {
        fight = new Fight();
    }

    public Player NewEnemy(JLabel L1, JLabel L2,
                           JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemyes();
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4,
                1, fight.getTotalEnemiesPerCurrentLocation(),
                fight.getCurrentLocation(), fight.getLocationsCount());
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }

    public Human NewHuman(JProgressBar pr1) {
        Human human = new Human(0, 80, 16, 1);
        action.HP(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        pr1.updateUI();
        return human;
    }

    public void EndGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        writeToTable(table);
        writeToExcel();
    }

    public void writeToExcel() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                XSSFRow r2 = sheet.createRow(i + 1);
                r2.createCell(0).setCellValue(i + 1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        File f = new File(filePath);
        book.write(new FileOutputStream(f));
        book.close();
    }

    public ArrayList<Result> getResults() {
        return this.results;
    }

    public void readFromExcel() throws IOException {
        try (XSSFWorkbook book = new XSSFWorkbook(filePath)) {
            XSSFSheet sh = book.getSheetAt(0);
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(),
                        (int) sh.getRow(i).getCell(2).getNumericCellValue()));
            }
        } catch (InvalidOperationException e) {
            createNewWorkBook();
        }
    }

    private void createNewWorkBook() {
        try (Workbook wb = new XSSFWorkbook();
             OutputStream fileOut = new FileOutputStream(filePath)) {
            Sheet sheet = wb.createSheet("Результаты ТОП 10");
            Row r = sheet.createRow(0);
            r.createCell(0).setCellValue("№");
            r.createCell(1).setCellValue("Имя");
            r.createCell(2).setCellValue("Количество баллов");
            wb.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }

    public void setLocationsCount(Integer locationsCount) {
        fight.setLocationsCount(locationsCount);
    }
}
