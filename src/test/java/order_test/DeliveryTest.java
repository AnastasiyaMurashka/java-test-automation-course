package order_test;

import order_task.Calculator;
import order_task.DeliveryServiceLoad;
import order_task.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryTest extends BaseTest {

    @Test
    @DisplayName("Test min cost of order")
    void testMinOrderCost() {
        Order order = new Order(2, false, false, DeliveryServiceLoad.USUAL_LOAD);
        assertEquals(400.00, Calculator.calculateDeliveryCost(order));
    }

    @DisplayName("Test cost of orders")
    @ParameterizedTest
    @MethodSource("orderGenerator")
    void testDistantCost(double actual, double expected) {
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test exception with 0 distance")
    void testNegativeDistance() {
        Order order = new Order(-1, true, true, DeliveryServiceLoad.HIGH_LOAD);
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> Calculator.calculateDeliveryCost(order));
        assertEquals("Distant can't be less or equal 0", thrown.getMessage());
    }

    @Test
    @DisplayName("Test exception with 30 km distance and fragile cargo")
    void testFragileCargoToLongDistant() {
        Order order = new Order(31, true, true, DeliveryServiceLoad.HIGH_LOAD);
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> Calculator.calculateDeliveryCost(order));
        assertEquals(thrown.getMessage(), "Fragile cargo should not be transported over "
            + "a distance of more than 30 km");
    }

    private static Stream<Arguments> orderGenerator() {
        return Stream.of(
            Arguments.of(Calculator.calculateDeliveryCost(new Order(2, true, true, DeliveryServiceLoad.VERY_HIGH_LOAD)), 880.00),
            Arguments.of(Calculator.calculateDeliveryCost(new Order(10, false, true, DeliveryServiceLoad.HIGH_LOAD)), 700.00),
            Arguments.of(Calculator.calculateDeliveryCost(new Order(30, true, false, DeliveryServiceLoad.INCREASED_LOAD)), 480.00));
    }
}
