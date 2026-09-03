package oops.polymorphism;

public class Polymorphism5 {
    static class DataStream{
        String rawData;
        DataStream(String rawData){
            this.rawData=rawData;
        }
        void process(){
            System.out.println("Processing generic data stream: " + rawData);
        }
    }
    static class JsonData extends DataStream{
        JsonData(String rawData){
            super(rawData);
        }
        @Override
        void process(){
            System.out.println("Parsing JSON payload: " + rawData);
        }
        void validateSchema(){
            System.out.println("Validating JSON Schema... Status: VALID ✅");
        }
    }
    static class CsvData extends DataStream{
        CsvData(String rawData){
            super(rawData);
        }
        @Override
        void process(){
            System.out.println("Parsing CSV row: " + rawData);
        }
        void countColumn(){
            System.out.println("Counting CSV columns... Found: 4 columns 📊");
        }
    }
    static class DataProcessor{
        void execute(DataStream ds){
            ds.process();
            if(ds instanceof JsonData json){
                json.validateSchema();
            }
            else if(ds instanceof CsvData csv){
                csv.countColumn();
            }
        }

        void execute(DataStream ds,boolean logDetails){
            execute(ds);
            if(logDetails==true){
                System.out.println("[LOG]: Execution completed successfully for " + ds.getClass().getSimpleName());
            }
        }
    }

    public static void main(String[] args) {
        DataProcessor d1=new DataProcessor();
        DataStream j=new JsonData("{\"id\": 101}");
        DataStream c=new CsvData("101,Rohan,Dev,50000");
        d1.execute(j);
        d1.execute(c,true);
    }
}
