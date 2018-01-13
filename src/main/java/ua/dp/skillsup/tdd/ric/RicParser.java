package ua.dp.skillsup.tdd.ric;

public class RicParser {
    public Ric parse(String code) {
        Ric ric = new Ric();
        ric.setCurrency(code.substring(0,3));
        ric.setTenor(code.substring(3,5));
        ric.setContributor(code.substring(6));
        return ric;
    }
}
