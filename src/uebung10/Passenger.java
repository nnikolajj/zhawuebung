package uebung10;

import lombok.Getter;

@Getter
public class Passenger {

    private String name;
    private String passportId;
    private boolean boarded;

    public Passenger(String name, String passportId) {
        this.name = name;
        this.passportId = passportId;
    }

    public boolean isBoarded() {
        return boarded;
    }

    public void setBoardedTrue() {
        boarded = true;
    }
}
