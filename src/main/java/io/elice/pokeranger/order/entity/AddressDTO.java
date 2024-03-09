package io.elice.pokeranger.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String postalCode;
    private String address1;
    private String address2;
    private String receiverName;
    private String receiverPhoneNumber;

}
