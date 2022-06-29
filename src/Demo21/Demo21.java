package Demo21;

// Подключение пакетов:
import java.awt.*;
import java.awt.event.*;
// Класс окна:
class PlotFrame extends Frame{
    // Конструктор (аргументы — высота и ширина окна):
    PlotFrame(int H,int W){
// Название окна:
        setTitle("График функции");
// Положение и размеры окна:
        setBounds(100,50,W,H);
// Цвет фона окна:
        setBackground(Color.GRAY);
// Отключение менеджера компоновки:
        setLayout(null);
// Объект шрифта:
        Font f=new Font("Arial",Font.BOLD,11);
// Применение шрифта:
        setFont(f);
// Окно фиксированных размеров:
        setResizable(false);
// Пиктограмма для окна:
        setIconImage(
                getToolkit().getImage(
                        "D:/Pictures/Icons/icon.png"
                )
        );
// Создание панели с кнопками:
        ButtonPanel BtnPnl=new ButtonPanel(6,25,W/4,H-30);
// Добавление панели в окно:
        add(BtnPnl);
// Создание панели для отображения графика:
        PlotPanel PltPnl=new PlotPanel(
                W/4+10,25,3*W/4-15,H-120,BtnPnl
        );
// Добавление панели в окно:
        add(PltPnl);
// Панель для отображения справки:
        HelpPanel HlpPnl=new HelpPanel(
                W/4+10,H-90,3*W/4-15,85
        );
// Добавление панели в окно:
        add(HlpPnl);
// Регистрация обработчика для окна:
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
// Завершение выполнения программы:
                System.exit(0);
            }
        });
// Регистрация обработчика для первой кнопки:
        BtnPnl.B1.addActionListener(
                new ButtonOneHandler(BtnPnl,PltPnl)
        );
// Регистрация обработчика для второй кнопки:
        BtnPnl.B2.addActionListener(new ButtonTwoHandler());
// Регистрация обработчика для опции
// отображения сетки:
        BtnPnl.Cbx[3].addItemListener(
                new CheckboxHandler(BtnPnl)
        );
// Отображение окна:
        setVisible(true);
    }
}
// Класс панели с кнопками:
class ButtonPanel extends Panel{
    // Массив меток:
    public Label[] L;
    // Группа переключателей:
    public CheckboxGroup CbxGrp;
    // Массив переключателей:
    public Checkbox[] Cbx;
    // Раскрывающийся список:
    public Choice Chc;
    // Текстовое поле:
    public TextField TxtFld;
    // Кнопки:
    public Button B1,B2;
    // Конструктор (аргументы — координаты
// и размеры панели):
    ButtonPanel(int x,int y,int W,int H){
// Отключение менеджера компоновки:
        setLayout(null);
// Положение и размер панели:
        setBounds(x,y,W,H);
// Цвет фона панели:
        setBackground(Color.LIGHT_GRAY);
// Создание массива меток:
        L=new Label[3];
// Первая текстовая метка:
        L[0]=new Label("Выбор цвета:",Label.CENTER);
// Шрифт для текстовой метки:
        L[0].setFont(new Font("Arial",Font.BOLD,12));
// Размеры метки:
        L[0].setBounds(5,5,getWidth()-10,30);
// Добавление метки на панель:
        add(L[0]);
// Создание группы переключателей:
        CbxGrp=new CheckboxGroup();
// Создание массива переключателей:
        Cbx=new Checkbox[4];
// Создание переключателей:
        Cbx[0]=new Checkbox(" красный ",CbxGrp,true);
        Cbx[1]=new Checkbox(" синий ",CbxGrp,false);
        Cbx[2]=new Checkbox(" черный ",CbxGrp,false);
// Создание опции отображения сетки:
        Cbx[3]=new Checkbox(" Сетка ",true);
// Задаем размеры и добавляем переключатели
// и опцию на панель:
        for(int i=0;i<4;i++){
            Cbx[i].setBounds(5,30+i*25,getWidth()-10,30);
            add(Cbx[i]);
        }
// Раскрывающийся список для определения
// цвета линий сетки:
        Chc=new Choice();
// Добавление элементов в список:
        Chc.add("Зеленый");
        Chc.add("Желтый");
        Chc.add("Серый");
// Размеры и положение раскрывающегося списка:
        Chc.setBounds(20,140,getWidth()-25,30);
// Добавление списка на панель:
        add(Chc);
// Вторая текстовая метка:
        L[1]=new Label("Интервал по х:",Label.CENTER);
// Шрифт для метки:
        L[1].setFont(new Font("Arial",Font.BOLD,12));
// Размеры и положение метки:
        L[1].setBounds(5,220,getWidth()-10,30);
// Добавление метки на панель:
        add(L[1]);
// Третья текстовая метка:
        L[2]=new Label("От x=0 до x=",Label.LEFT);
// Размеры и положение метки:
        L[2].setBounds(5,250,70,20);
// Добавление метки на панель:
        add(L[2]);
// Текстовое поле для ввода верхней границы
// диапазона изменения аргумента функции:
        TxtFld=new TextField("10");
// Размеры и положение поля:
        TxtFld.setBounds(75,250,45,20);
// Добавление поля на панель:
        add(TxtFld);
// Первая кнопка ("Нарисовать"):
        B1=new Button("Нарисовать");
// Вторая кнопка ("Закрыть"):
        B2=new Button("Закрыть");
// Размеры и положение первой кнопки:
        B1.setBounds(5,getHeight()-75,getWidth()-10,30);
// Размеры и положение второй кнопки:
        B2.setBounds(5,getHeight()-35,getWidth()-10,30);
// Добавление первой кнопки на панель:
        add(B1);
// Добавление второй кнопки на панель:
        add(B2);
    }
}
// Класс панели для отображения графика:
class PlotPanel extends Panel{
    // Ссылка на объект внутреннего класса:
    public Plotter G;
    // Внутренний класс для реализации графика функции:
    class Plotter{
        // Границы диапазона изменения координат:
        private double Xmin=0,Xmax,Ymin=0,Ymax=1.0;
        // Состояние опции отображения сетки:
        private boolean status;
        // Цвет для отображения графика:
        private Color clr;
        // Цвет для отображения линий сетки:
        private Color gClr;
        // Конструктор (аргумент — ссылка на панель
// с кнопками):
        Plotter(ButtonPanel P){
// Считывание значения текстового поля
// и преобразование в число:
            try{
                Xmax=Double.parseDouble(P.TxtFld.getText());
            }
            catch(NumberFormatException e){
                P.TxtFld.setText("10");
                Xmax=10;
            }
// Определение состояния опции:
            status=P.Cbx[3].getState();
// Определение цвета линий сетки:
            switch(P.Chc.getSelectedIndex()){
                case 0:
                    gClr=Color.GREEN;
                    break;
                case 1:
                    gClr=Color.YELLOW;
                    break;
                default:
                    gClr=Color.GRAY;
            }
// Цвет линии графика:
            String name=
                    P.CbxGrp.getSelectedCheckbox().getLabel();
            if(name.equalsIgnoreCase(" красный ")){
                clr=Color.RED;
            }else{
                if(name.equalsIgnoreCase(" синий ")){
                    clr=Color.BLUE;
                }else{
                    clr=Color.BLACK;
                }
            }
        }
        // Метод определяет отображаемую
// на графике функцию:
        private double f(double x){
            return (1+Math.sin(x))/(1+Math.abs(x));
        }
        // Метод для считывания и запоминания настроек:
        public Plotter remember(ButtonPanel P){
            return new Plotter(P);
        }
        // Метод для отображения графика и сетки:
        public void plot(Graphics Fig){
// Параметры области отображения графика:
            int H,W,h,w,s=20;
            H=getHeight();
            W=getWidth();
            h=H-2*s;
            w=W-2*s;
// Очистка области графика:
            Fig.clearRect(0,0,W,H);
// Индексная переменная и количество линий сетки:
            int k,nums=10;
// Цвет координатных осей — черный:
            Fig.setColor(Color.BLACK);
// Отображение координатных осей:
            Fig.drawLine(s,s,s,h+s);
            Fig.drawLine(s,s+h,s+w,s+h);
// Отображение засечек и числовых значений
// на координатных осях:
            for(k=0;k<=nums;k++){
                Fig.drawLine(s+k*w/nums,s+h,s+k*w/nums,s+h+5);
                Fig.drawLine(s-5,s+k*h/nums,s,s+k*h/nums);
                Fig.drawString(
                        Double.toString(Xmin+k*(Xmax-Xmin)/nums),
                        s+k*w/nums-5,s+h+15
                );
                Fig.drawString(
                        Double.toString(Ymin+k*(Ymax-Ymin)/nums),
                        s-17,s+h-1-k*h/nums
);
            }
// Отображение сетки (если установлена опция):
            if(status){
                Fig.setColor(gClr);
// Отображение линий сетки:
                for(k=1;k<=nums;k++){
                    Fig.drawLine(s+k*w/nums,s,s+k*w/nums,h+s);
                    Fig.drawLine(
                            s,s+(k-1)*h/nums,s+w,s+(k-1)*h/nums
                    );
                }
            }
// Отображение графика:
            Fig.setColor(clr); // Установка цвета линии
// Масштаб на один пиксел по каждой из координат:
            double dx=(Xmax-Xmin)/w,dy=(Ymax-Ymin)/h;
// Переменные для записи декартовых координат:
            double x1,x2,y1,y2;
// Переменные для записи координат
// в окне отображения графика:
            int h1,h2,w1,w2;
// Начальные значения:
            x1=Xmin;
            y1=f(x1);
            w1=s;
            h1=h+s-(int)Math.round(y1/dy);
// Шаг в пикселах для базовых точек:
            int step=5;
// Отображение базовых точек
// и соединение их линиями:
            for(int i=step;i<=w;i+=step){
                x2=i*dx;
                y2=f(x2);
                w2=s+(int)Math.round(x2/dx);
                h2=h+s-(int)Math.round(y2/dy);
// Линия:
                Fig.drawLine(w1,h1,w2,h2);
// Базовая точка (квадрат):
                Fig.drawRect(w1-2,h1-2,4,4);
// Новые значения для координат:
                x1=x2;
                y1=y2;
                w1=w2;
                h1=h2;
            }
        }
    }
    // Конструктор (аргументы — координаты и размеры
// панели, а также ссылка на панель с кнопками):
    PlotPanel(int x,int y,int W,int H,ButtonPanel P){
// Создание объекта внутреннего класса:
        G=new Plotter(P);
// Цвет фона панели:
        setBackground(Color.WHITE);
// Размеры и положение панели:
        setBounds(x,y,W,H);
    }
    // Переопределение метода для перерисовки панели:
    public void paint(Graphics g){
// При перерисовке панели вызывается метод
// для отображения графика:
        G.plot(g);
    }
}
// Класс для панели справки:
class HelpPanel extends Panel{
    // Метка:
    public Label L;
    // Текстовая область:
    public TextArea TxtA;
    // Конструктор (аргументы — координаты
// и размеры панели):
    HelpPanel(int x,int y,int W,int H){
// Цвет фона панели:
        setBackground(Color.LIGHT_GRAY);
// Размеры и положение панели:
        setBounds(x,y,W,H);
// Отключения менеджера компоновки:
        setLayout(null);
// Метка для панели:
        L=new Label("СПРАВКА",Label.CENTER);
// Размеры и положение метки:
        L.setBounds(0,0,W,20);
// Добавление метки на панель:
        add(L);
// Текстовая область для панели:
        TxtA=new TextArea(
                " График функции y(x)=(1+sin(x))/(1+|x|)"
        );
// Шрифт для текстовой области:
        TxtA.setFont(new Font("Serif",Font.PLAIN,15));
// Размер и положение текстовой области:
        TxtA.setBounds(5,20,W-10,60);
// Область недоступна для редактирования:
        TxtA.setEditable(false);
// Добавление текстовой области на панель:
        add(TxtA);
    }
}
// Класс обработчика для первой кнопки:
class ButtonOneHandler implements ActionListener{
    // Панель с кнопками:
    private ButtonPanel P1;
    // Панель для отображения графика:
    private PlotPanel P2;
    // Конструктор класса (аргументы — ссылки на панели):
    ButtonOneHandler(ButtonPanel P1,PlotPanel P2){
        this.P1=P1;
        this.P2=P2;
    }
    // Метод для обработки нажатия кнопки:
    public void actionPerformed(ActionEvent ae){
// Обновление параметров (настроек)
// для отображения графика:
        P2.G=P2.G.remember(P1);
// Рисование графика:
        P2.G.plot(P2.getGraphics());
    }
}
// Класс обработчика для второй кнопки:
class ButtonTwoHandler implements ActionListener{
    // Метод для обработки нажатия кнопки:
    public void actionPerformed(ActionEvent ae){
// Завершение выполнения программы:
        System.exit(0);
    }
}
// Класс обработчика для опции отображения сетки:
class CheckboxHandler implements ItemListener{
    // Список выбора цвета для сетки:
    private Choice ch;
    // Конструктор (аргумент — ссылка на панель
// с кнопками):
    CheckboxHandler(ButtonPanel P){
        this.ch=P.Chc;
    }
    // Метод для обработки изменения состояния опции:
    public void itemStateChanged(ItemEvent ie){
        ch.setEnabled(
                ie.getStateChange()==ItemEvent.SELECTED
        );
    }
}
// Главный класс:
class Demo21{
    public static void main(String args[]){
// Создание (и отображение) окна:
        new PlotFrame(600,1200);
    }
}
