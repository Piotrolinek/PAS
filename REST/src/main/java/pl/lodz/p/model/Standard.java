package pl.lodz.p.model;

import java.util.UUID;

public class Standard extends ClientType{

    public Standard() {
        super(new MongoUUID(UUID.randomUUID()), 3, "Standard");
    }



    @Override
    public String toString() {
        return "Standard" + this.getClass().getSimpleName();
    }
}