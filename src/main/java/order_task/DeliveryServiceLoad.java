package order_task;

public enum DeliveryServiceLoad {
    VERY_HIGH_LOAD(1.6),
    HIGH_LOAD(1.4),
    INCREASED_LOAD(1.2),
    USUAL_LOAD(1);

    private double rate;

    DeliveryServiceLoad(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
