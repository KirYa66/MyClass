package Demo5;

// Класс:
class MyClass{
    // Поле:
    int code;
    // Методы:
    void set(int n){
        code=n;
    }
    void display(){
        System.out.println("Поле: "+code);
    }
    // Статический метод:
    static void show(String t){
        System.out.println("MyClass: "+t);
    }
}
// Интерфейсы:
interface Alpha{
    void alpha(MyClass obj,int n);
}
interface Bravo{
    void bravo(MyClass obj);
}
interface Charlie{
    void charlie(String t);
}
// Главный класс:
class Demo5{
    public static void main(String[] args){
// Создание объекта:
        MyClass obj=new MyClass();
// Интерфейсные переменные:
        Alpha A=MyClass::set;
        Bravo B=MyClass::display;
        Charlie C=MyClass::show;
// Вызов методов:
        obj.set(123);
        B.bravo(obj);
        A.alpha(obj,321);
        obj.display();
        C.charlie("Hello");
    }
}
