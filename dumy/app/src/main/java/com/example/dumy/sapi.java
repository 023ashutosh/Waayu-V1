package com.example.dumy;

public class sapi {

    private String fever;
    private String pneumonia;
    private String stress;
    private String hypoxia;

    public sapi(String fever, String pneumonia, String stress, String hypoxia) {
        this.fever = fever;
        this.pneumonia = pneumonia;
        this.stress = stress;
        this.hypoxia = hypoxia;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getPneumonia() {
        return pneumonia;
    }

    public void setPneumonia(String pneumonia) {
        this.pneumonia = pneumonia;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public String getHypoxia() {
        return hypoxia;
    }

    public void setHypoxia(String hypoxia) {
        this.hypoxia = hypoxia;
    }
}
