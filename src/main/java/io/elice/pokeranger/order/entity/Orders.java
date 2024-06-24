package io.elice.pokeranger.order.entity;

import io.elice.pokeranger.global.entity.BaseEntity;
import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import io.elice.pokeranger.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMessage;
    private int totalCost;
    private String summaryTitle;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStateRole deliveryState;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Orders(String orderMessage, int totalCost, String summaryTitle, User user, Address address) {
        this.orderMessage = orderMessage;
        this.totalCost = totalCost;
        this.summaryTitle = summaryTitle;
        this.user = user;
        this.address = address;
        this.deliveryState = DeliveryStateRole.PREPARE;
    }


}
