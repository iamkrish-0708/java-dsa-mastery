package oops.abstraction;


public class Abstraction5 {
    interface Searchable{
        boolean search(int target);
    }

    static abstract class CustomDataStructure{
        protected int size;
        //constructor
        CustomDataStructure(){
            this.size=0;
        }
        //concrete method
        int getSize(){
            return this.size;
        }
        //abstract methods
        abstract void add(int value);
        abstract void printStructure();

    }

    static class IntArrayList extends CustomDataStructure implements Searchable {
        private int[] data;

        IntArrayList(int capacity) {
            super();
            this.data = new int[capacity];
        }

        @Override
        void add(int value) {
            if (size < data.length) {
                data[size] = value;
                size++;
            }
        }

        @Override
        void printStructure() {
            for (int i = 0; i < size; i++) {
                System.out.println(data[i]);
            }
        }

        @Override
        public boolean search(int target) {
            for (int i = 0; i <= size; i++) {
                if (data[i] == target) {
                    return true;
                }
            }return false;
        }


    }

    public static void main(String[] args) {
        IntArrayList arr=new IntArrayList(5);
        arr.add(10);
        arr.add(25);
        arr.add(50);
        arr.printStructure();
        System.out.println(arr.getSize());
        System.out.println(arr.search(25));
        System.out.println(arr.search(99));
    }
}
