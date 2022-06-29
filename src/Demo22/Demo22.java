package Demo22;

// Подключение пакетов:

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Класс для окна:
class CalculatorFrame extends JFrame {
    // Конструктор:
    CalculatorFrame() {
// Размеры окна:
        int w = 270, h = 240;
// Название окна:
        setTitle("Калькулятор");
// Определение положения и размеров окна:
        setBounds(100, 100, w, h);
// Реакция на нажатие системной пиктограммы:
        setDefaultCloseOperation(EXIT_ON_CLOSE);
// Создание панели с кнопками и полем:
        CalcPanel panel = new CalcPanel(w, h);
// Добавление панели в окно:
        add(panel);
// Окно фиксированных размеров:
        setResizable(false);
// Отображение окна:
        setVisible(true);
    }
}

// Класс панели:
class CalcPanel extends JPanel {
    // Текстовое поле:
    public JTextField TxtFld;
    // Обработчик нажатия кнопки:
    private BtnAction BtnPressed;

    // Конструктор (аргументы — размеры панели):
    CalcPanel(int W, int H) {
// Размеры кнопок и отступы:
        int w = W / 5, h = H / 8, sx = w / 5, sy = h / 3;
// Отключение менеджера компоновки:
        setLayout(null);
// Рамка вокруг панели:
        setBorder(BorderFactory.createEtchedBorder());
// Создание текстового поля:
        JTextField TxtFld = new JTextField();
// Выравнивание текста в поле по правому краю:
        TxtFld.setHorizontalAlignment(JTextField.RIGHT);
// Положение и размеры поля:
        TxtFld.setBounds(sx, sy, 2 * sx + 3 * w, h);
// Отмена возможности редактирования поля:
        TxtFld.setEditable(false);
// Добавление поля на панель:
        add(TxtFld);
// Создание обработчика нажатия кнопки:
        BtnPressed = new BtnAction(TxtFld);
// Массив с названиями кнопок:
        String[] BtnTxt = {
                "1", "2", "3", "+", "4", "5", "6", "-",
                "7", "8", "9", "/", "0", ".", "=", "*"};
// Создание кнопок и добавление их на панель:
        for (int i = 0; i < BtnTxt.length; i++) {
            addBtn(sx + (w + sx) * (i % 4), (2 * sy + h) + (sy + h) * (i / 4),
                    w, h, BtnTxt[i], BtnPressed);
        }
// Создание кнопки сброса параметров:
        JButton BtnC = new JButton("C");
// Положение и размеры кнопки:
        BtnC.setBounds(4 * sx + 3 * w, sy, w, h);
// Добавление обработчика для кнопки:
        BtnC.addActionListener(BtnPressed);
// Режим отмены отображения рамки фокуса:
        BtnC.setFocusPainted(false);
// Красный цвет для названия кнопки:
        BtnC.setForeground(Color.RED);
// Добавление кнопки на панель:
        add(BtnC);
    }

    // Метод для создания и добавления кнопок на панель
// (аргументы — положение и размеры кнопки,
// название и обработчик нажатия кнопки):
    void addBtn(int i, int j, int w, int h,
                String txt,
                ActionListener AcList) {
// Создание кнопки:
        JButton b = new JButton(txt);
// Положение и размеры кнопки:
        b.setBounds(i, j, w, h);
// Режим отмены отображения рамки фокуса:
        b.setFocusPainted(false);
// Добавление обработчика для кнопки:
        b.addActionListener(AcList);
// Добавление кнопки на панель:
        add(b);
    }
}

class BtnAction implements ActionListener {
    // Текстовое поле для отображения информации:
    public JTextField TxtFld;
    // Индикатор состояния ввода числа:
    private boolean start;
    // Индикатор состояния ввода десятичной точки:
    private boolean point;
    // Текстовое представление последнего
// введенного оператора:
    private String cmd;
    // Поле для записи промежуточного результата:
    private double result;

    // Метод для сброса параметров:
    private void onStart() {
        start = true;
        point = true;
        cmd = "C";
        result = 0;
        TxtFld.setText("0.0");
    }

    // Метод для вычисления результата последней операции:
    private void calc() {
// Введенное в поле число:
        double x;
        x = Double.parseDouble(TxtFld.getText());
// Вычисление результата:
        if (cmd.equals("*")) result *= x;
        else if (cmd.equals("/")) result /= x;
        else if (cmd.equals("-")) result -= x;
        else if (cmd.equals("+")) result += x;
        else result = x;
// Заполнение текстового поля:
        TxtFld.setText(Double.toString(result));
    }

    // Конструктор (аргумент — ссылка на текстовое поле):
    BtnAction(JTextField TxtFld) {
        this.TxtFld = TxtFld;
        onStart();
    }

    // Реакция на нажатие кнопки:
    public void actionPerformed(ActionEvent ae) {
// Считывание текста на кнопке:
        String str = ae.getActionCommand();
// Проверка вариантов:
        if (str.equals("C")) { // Кнопка сброса
            onStart();
            return;
        }
// Вычисление результата:
        if (str.equals("+") | str.equals("-") | str.equals("*") |
                str.equals("/") | str.equals("=")) {
            calc();
            cmd = str;
            start = true;
            point = true;
            return;
        }
// Ввод числа:
        if (start) { // Начало ввода числа
// Ввод точки в начале ввода числа:
            if (str.equals(".")) {
                TxtFld.setText("0.");
                point = false;
                start = false;
                return;
            } else { // Ввод цифры в начале ввода числа
                TxtFld.setText(str);
                start = false;
                return;
            }
        } else { // Продолжение ввода числа
            if (str.equals(".")) { // Попытка ввести точку
                str = point ? str : "";
                point = false;
            }
// Добавление цифры к числу:
            if (TxtFld.getText().equals("0") & !str.equals(".")) {
                TxtFld.setText(str);
            } else {
                TxtFld.setText(TxtFld.getText() + str);
            }
        }
    }
}

// Главный класс:
class Demo22 {
    public static void main(String[] args) {
// Создание (и отображение) окна:
        new CalculatorFrame();
    }
}