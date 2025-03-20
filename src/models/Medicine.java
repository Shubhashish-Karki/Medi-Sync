package models;
// import java.sql.Time;
import java.sql.Date;


public class Medicine {
    private int med_id;
    private String med_name;
    private String med_dosage;
    private String frequency;
    private Date start_date;
    private Date end_date;
    private String notes;

    public Medicine(int med_id, String med_name, String med_dosage, String frequency, Date start_date, Date end_date, String notes) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.med_dosage = med_dosage;
        this.frequency = frequency;
        this.start_date = start_date;
        this.end_date = end_date;
        this.notes = notes;
    }

    public int getMed_id() {
        return med_id;
    }

    public String getMed_name() {
        return med_name;
    }
    
    public String getMed_dosage() {
        return med_dosage;
    }

    public String getMed_frequency() {
        return frequency;
    }
    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getNotes() {
        return notes;
    }



}
