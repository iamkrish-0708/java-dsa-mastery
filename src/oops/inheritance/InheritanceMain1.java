package oops.inheritance;

class HospitalStaff {
    String name;
    int staffId;
    void clockIn() {
        System.out.printf("%n%s (ID:%d) clocked in for shift.", this.name, this.staffId);
    }
}

class Doctor extends HospitalStaff {
    String specialization;
    void performCheckup(String patientName) {
        System.out.printf("%n%s (%s) is conducting checkup on patient %s",
                this.name, this.specialization, patientName);
    }
}

public class InheritanceMain1 {
    public static void main(String[] args) {
        Doctor dr1=new Doctor();
        dr1.name = "Dr. Sharma";
        dr1.staffId = 501;
        dr1.specialization = "Cardiology";
        dr1.clockIn();
        dr1.performCheckup("Rahul");
    }
}
