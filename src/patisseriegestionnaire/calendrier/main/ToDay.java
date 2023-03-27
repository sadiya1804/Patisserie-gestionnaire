package patisseriegestionnaire.calendrier.main;

public class ToDay {
    
     public ToDay(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public int getDay() {
        return jour;
    }

    public void setDay(int jour) {
        this.jour = jour;
    }

    public int getMonth() {
        return mois;
    }

    public void setMonth(int mois) {
        this.mois = mois;
    }

    public int getYear() {
        return annee;
    }

    public void setYear(int annee) {
        this.annee = annee;
    }

   

    public ToDay() {
    }

    private int jour;
    private int mois;
    private int annee;

    public boolean isToDay(ToDay date) {
        return jour == date.getDay() && mois == date.getMonth() && annee == date.getYear();
    }
}
