package Entity;

import java.util.ArrayList;
import java.util.Objects;

public class Resume {
    public final int SUM_OF_CELLS = 12;
    private String paymentFrom = "Не указано";
    private String paymentTo = "Не указано";
    private String phone = "Не указано";
    private String currency = "Не указано";
    private String profession = "Не указано";
    private String compensation = "Не указано";
    private ArrayList<String> metro;
    private String candidat = "Не указано";
    private String typeOfWork = "Не указано";
    private String education = "Не указано";
    private String experience = "Не указано";
    private String town = "Не указано";


    public Resume() {
        metro = new ArrayList<>();
    }

    public String getPaymentFrom() {
        return paymentFrom;
    }

    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    public String getPaymentTo() {
        return paymentTo;
    }

    public void setPaymentTo(String paymentTo) {
        this.paymentTo = paymentTo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public ArrayList<String> getMetro() {
        return metro;
    }

    public void addMetro(String metro) {
        this.metro.add(metro);
    }

    public String getCandidat() {
        return candidat;
    }

    public void setCandidat(String candidat) {
        this.candidat = candidat;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;

        if (candidat != null ? candidat.equals(resume.candidat) : resume.candidat != null) return false;
        return candidat != null ? candidat.equals(resume.candidat) : resume.candidat == null;


    }

    @Override
    public int hashCode() {
        int result = candidat != null ? candidat.hashCode() : 0;
        result = 51 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public ArrayList<String> resumeToArray() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, this.paymentFrom);
        list.add(1, this.paymentTo);
        list.add(2, this.phone);
        list.add(3, this.currency);
        list.add(4, this.profession);
        list.add(5, this.compensation);

        if (metro.isEmpty()) {
            list.add(6, "Нет");
        } else {
            String metro = "";
            for (int i = 0; i < this.metro.size(); i++) {
                metro += this.metro.get(i) + "; ";
            }
            list.add(6, metro);
        }


        list.add(7, this.candidat);
        list.add(8, this.typeOfWork);
        list.add(9, this.education);
        list.add(10, this.experience);
        list.add(11, this.town);
        return list;
    }

    //Использовал для проверки содержимого List
    public void printResumeToArray(ArrayList<String> arrayList) {
        for (String element : arrayList) {
            System.out.println(element);
        }
    }


}
