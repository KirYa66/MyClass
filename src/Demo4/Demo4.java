package Demo4;
// Интерфейсы:
interface Alpha{
    void apply(String t);
}
interface Bravo{
    void display();
}
// Класс:
class MyClass{
    // Поле:
    String name;
    // Методы:
    void set(String t){
        name=t;
    }
    void show(){
        System.out.println("Имя: "+name);
    }
}
// Главный класс:
class Demo4{
    public static void main(String[] args){
// Создание объекта:
        MyClass obj=new MyClass();
// Интерфейсные переменные и ссылки на методы:
        Alpha A=obj::set;
        Bravo B=obj::show;
// Вызов методов:
        A.apply("Красный");
        obj.show();
        System.out.println(obj.toString());
        obj.set("Желтый");
        B.display();
        System.out.println(B.toString());
// Создание нового объекта:
        obj=new MyClass();
// Вызов методов:
        obj.set("Зеленый");
        B.display();
        System.out.println(B.toString());
        A.apply("Синий");
        obj.show();
        System.out.println(obj.toString());
        B.display();
        System.out.println(B.toString());

    }
}

