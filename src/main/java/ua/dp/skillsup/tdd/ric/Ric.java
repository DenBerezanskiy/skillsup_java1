package ua.dp.skillsup.tdd.ric;

public class Ric {
    private String currency;
    private String tenor;
    private String contributor;

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTenor() {
        return tenor;
    }

    public String getContributor() {
        return contributor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

}
