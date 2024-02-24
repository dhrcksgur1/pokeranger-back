package io.elice.pokeranger.order.entity;

import io.elice.pokeranger.order.deliverystate.DeliveryStateRole;
import io.elice.pokeranger.orderItem.entity.OrderItem;
import io.elice.pokeranger.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderMessage;
    private int totalCost;

    @Enumerated(EnumType.STRING)
    private DeliveryStateRole deliveryState;

    @CreatedDate
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> Items = new ArrayList<>();

    public Orders(String orderMessage, int totalCost, User user) {
        this.orderMessage = orderMessage;
        this.totalCost = totalCost;
        this.user = user;
        this.deliveryState = DeliveryStateRole.PREPARE;
    }

}
