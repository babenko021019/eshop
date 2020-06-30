package info.unproj.model;

import lombok.Getter;

@Getter
public enum CartStatus {

    OPEN("open"),
    PAID("paid");

    private final String name;

    CartStatus(String name) {
        this.name = name;
    }

    public CartStatus getByValue(String value){
        return CartStatus.valueOf(value);
    }
}
