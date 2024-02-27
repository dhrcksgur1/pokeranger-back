package io.elice.pokeranger.enums;

import lombok.Getter;

@Getter
public enum UserType {

    /** 관리자 계정 타입  */
    Admin("Admin") ,

    /** 일반 계정 타입  */
    User("User");

    private final String typeName;
    UserType(String typeName) {
        this.typeName = typeName;
    }
}
