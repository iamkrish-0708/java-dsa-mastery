package oops.inheritance;

class DataStructure{
    protected String dsName;
    protected int capacity;

    DataStructure(String dsName, int capacity){
        this.dsName=dsName;
        this.capacity=capacity;
    }

    void printOverview(){
        System.out.println("Data Structure: " + this.dsName + " | Max Capacity: " + this.capacity);
    }
}

class StackStructure extends DataStructure{
    private int topIndex;

    StackStructure(int capacity){
        super("LIFO Stack",capacity);
        this.topIndex=-1;
    }

    @Override
    void printOverview(){
        super.printOverview();
        System.out.println("Type: LIFO (Last-In, First-Out) | Current Top Index: " + this.topIndex);
    }
}

class QueueStructure extends DataStructure{
    private int frontIndex;
    private int rearIndex;

    QueueStructure( int capacity){
        super("FIFO Queue", capacity);
        this.frontIndex=0;
        this.rearIndex=0;
    }

    @Override
    void printOverview(){
        super.printOverview();
        System.out.println("Type: FIFO (First-In, First-Out) | Front: " + this.frontIndex + " | Rear: " + this.rearIndex);
    }
}

public class InheritanceMain5 {
    public static void main(String[] args) {
        DataStructure[] list = {
                new StackStructure(10),
                new QueueStructure(20)
        };

        for (DataStructure ds : list) {
            ds.printOverview(); // 👈 Java automatically calls Stack's printOverview(), then Queue's printOverview()!
        }
    }
}
