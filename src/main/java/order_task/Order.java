package order_task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {

    private int distance;
    private boolean isBigSize;
    private boolean isFragileCargo;
    private DeliveryServiceLoad deliveryServiceLoad;
}
