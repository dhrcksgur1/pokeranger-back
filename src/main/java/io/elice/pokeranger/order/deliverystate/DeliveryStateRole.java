package io.elice.pokeranger.order.deliverystate;

import lombok.Getter;

@Getter
public enum DeliveryStateRole {
    PREPARE("상품 준비중"),
    DELIVERY("상품 배송중"),
    COMPLETE("배송완료");

    private final String description;

    DeliveryStateRole(String description) {
        this.description = description;
    }

    public static DeliveryStateRole fromDescription(String description) {
        for (DeliveryStateRole state : DeliveryStateRole.values()) {
            if (state.getDescription().equals(description)) {
                return state;
            }
        }
        throw new IllegalArgumentException("해당 설명에 맞는 배송 상태가 없습니다: " + description);
    }
}