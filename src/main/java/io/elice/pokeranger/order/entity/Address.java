package io.elice.pokeranger.order.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String postalCode;
    private String address1;
    private String address2;
    private String receiverName;
    private String receiverPhoneNumber;
}
