package Demo2;

// Суперкласс:
    class Alpha{
        char symb;
        void set(char s){
            symb=s;
        }
        void show(){
            System.out.println("Класс Demo2.Alpha");
            System.out.println("Символ: "+symb);
        }
    }
    // Подкласс:
    class Bravo extends Alpha{
        int num;
        void set(char s,int n){
            symb=s;
            num=n;
        }
        void show(){
            System.out.println("Класс Demo2.Bravo");
            System.out.println("Символ: "+symb);
            System.out.println("Число: "+num);
        }
    }
    // Главный класс:
    class Demo2{
        public static void main(String[] args){
// Объектная переменная суперкласса:
            Alpha A;
// Создание объекта подкласса:
            Bravo B=new Bravo();
// В объектную переменную суперкласса
// записывается ссылка на объект подкласса:
            A=B;
// Вызов методов через переменную подкласса:
            B.set('B',100);
            B.show();
// Вызов методов через переменную суперкласса:
            A.set('A');
            A.show();
            B.show();
        }
    }


