package io.elice.pokeranger.orderItem.entity;

import io.elice.pokeranger.order.entity.Orders;
import io.elice.pokeranger.prodcut.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem(Product product, Orders order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }
}
