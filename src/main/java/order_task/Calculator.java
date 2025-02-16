package order_task;

import java.text.DecimalFormat;

public class Calculator {

    private static final int MIN_SUM = 400;

    public static double calculateDeliveryCost(Order order) {
        if (order.getDistance() > 30 && order.isFragileCargo()) {
            throw new IllegalArgumentException("Fragile cargo should not be transported over "
                + "a distance of more than 30 km");
        }
        double sum = (defineDistantCost(order.getDistance()) + defineSizeCost(order.isBigSize())
            + defineFragileCost(order.isFragileCargo())) * order.getDeliveryServiceLoad().getRate();
        DecimalFormat df = new DecimalFormat("###");
        return Math.max(Double.parseDouble(df.format(sum)), MIN_SUM);
    }

    private static int defineDistantCost(int distant) {
        if (distant <= 0) {
            throw new IllegalArgumentException("Distant can't be less or equal 0");
        } else if (distant <= 2) {
            return 50;
        }
        if (distant <= 10) {
            return 100;
        }
        if (distant <= 30) {
            return 200;
        } else return 300;
    }

    private static int defineSizeCost(boolean isBigSize) {
        return isBigSize ? 200 : 100;
    }

    private static int defineFragileCost(boolean isFragile) {
        return isFragile ? 300 : 0;
    }
}
