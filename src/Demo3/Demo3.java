package Demo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;
class Demo3{
    public static void main(String[] args){
// Текст для метки:
        String msg="Текст синего цвета";
        String txt="Текст красного цвета";
// Создание текстовой метки:
        JLabel label=new JLabel(msg);
// Режим выравнивания текста в метке по центру:
        label.setHorizontalAlignment(JLabel.LEFT);
// Синий цвет для текста метки:
        label.setForeground(Color.blue);
// Создание объекта шрифта:
        Font F=new Font("Arial",Font.BOLD|Font.ITALIC,18);
// Применение шрифта к метке:
        label.setFont(F);
// Рамка вокруг метки:
        label.setBorder(BorderFactory.createEtchedBorder());
// Создание обработчика для метки:
        MouseAdapter handler=new MouseAdapter(){
            // Метод вызывается при наведении курсора
// на область метки:
            public void mouseEntered(MouseEvent e){
// Текст для метки:
                label.setText(txt);
// Красный цвет шрифта для метки:
                label.setForeground(Color.red);
                label.setHorizontalAlignment(JLabel.RIGHT);
            }
            // Метод вызывается при выходе курсора
// за область метки:
            public void mouseExited(MouseEvent e){
// Текст для метки:
                label.setText(msg);
// Синий цвет шрифта для метки:
                label.setForeground(Color.blue);
                label.setHorizontalAlignment(JLabel.LEFT);
            }
        };
// Регистрация обработчика в метке:
        label.addMouseListener(handler);
// Отображение сообщения:
        showMessageDialog(null, // Родительское окно
                label, // Отображается метка
                "Сообщение", // Название окна
                PLAIN_MESSAGE // Нет пиктограммы
        );
    }
}

