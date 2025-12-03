public class OblastPohybu {
    private int lavyOkraj;
    private int pravyOkraj;
    private int hornyOkraj;
    private int dolnyOkraj;

    public OblastPohybu(int lavyOkraj, int pravyOkraj, int hornyOkraj, int dolnyOkraj) {
        this.lavyOkraj = lavyOkraj;
        this.pravyOkraj = pravyOkraj;
        this.hornyOkraj = hornyOkraj;
        this.dolnyOkraj = dolnyOkraj;
    }

    public int getLavyOkraj() {
        return this.lavyOkraj;
    }

    public int getPravyOkraj() {
        return this.pravyOkraj;
    }

    public int getHornyOkraj() {
        return this.hornyOkraj;
    }

    public int getDolnyOkraj() {
        return this.dolnyOkraj;
    }
}
