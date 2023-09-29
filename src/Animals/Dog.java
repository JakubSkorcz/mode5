public class Dog implements Animal{
    @Override
    public void speak(){
        System.out.println("Woof!");
    }

    public void move(){
        System.out.println("Walks");
    }

}
