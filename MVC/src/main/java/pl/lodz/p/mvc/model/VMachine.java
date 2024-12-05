package pl.lodz.p.mvc.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "_clazz"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AppleArch.class, name = "applearch"),
        @JsonSubTypes.Type(value = x86.class, name = "x86")
})
public class VMachine extends AbstractEntityMgd {

    private int CPUNumber;
    private String ramSize;
    private int isRented;
    protected float actualRentalPrice;

    public VMachine(int CPUNumber, String ramSize, int isRented) {
        super(new MongoUUID(UUID.randomUUID()));
        this.CPUNumber = CPUNumber;
        this.ramSize = ramSize;
        this.isRented = isRented;
    }

    public VMachine() {
        super(new MongoUUID(UUID.randomUUID()));
    }

    public VMachine(MongoUUID uuid, int CPUNumber,
                    String ramSize, int isRented) {
        super(uuid);
        this.CPUNumber = CPUNumber;
        this.ramSize = ramSize;
        this.isRented = isRented;
    }

    public int isRented() {
        return isRented;
    }

    public void setRented(int rented) {
        isRented = rented;
    }

    public float getActualRentalPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return "VMachine{" +
                "CPUNumber=" + CPUNumber +
                ", ramSize='" + ramSize + '\'' +
                ", isRented=" + isRented +
                ", actualRentalPrice=" + actualRentalPrice +
                '}';
    }
};

