package io.elice.pokeranger.user.entity;

import io.elice.pokeranger.global.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponceDTO {
    @NotNull

    private String token;

    @NotNull
    private UserType type;

    private Long userId;
}
