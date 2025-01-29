package net.harupiza.hikouki.aircraft.plane;

import net.harupiza.hikouki.aircraft.Aircraft;
import net.harupiza.hikouki.aircraft.SeparatedPart;
import net.harupiza.hikouki.aircraft.parts.CustomModel;
import net.harupiza.hikouki.aircraft.parts.PartBasic;
import net.harupiza.hikouki.aircraft.parts.Seat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Crime320 extends Aircraft {
    public Crime320(@Nonnull CustomModel main, @Nonnull SeparatedPart<CustomModel> landingGear, @Nonnull SeparatedPart<Seat> pilotSeat, @Nullable SeparatedPart<Seat> coPilotSeat) {
        super(main, landingGear, pilotSeat, coPilotSeat);
    }

    @Override
    public ArrayList<SeparatedPart<? extends PartBasic>> initParts() {
        return new ArrayList<>(List.of(
        ));
    }
}
