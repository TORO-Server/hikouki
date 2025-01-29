package net.harupiza.hikouki.aircraft;

import net.harupiza.hikouki.Hikouki;
import net.harupiza.hikouki.aircraft.parts.CustomModel;
import net.harupiza.hikouki.aircraft.parts.PartBasic;
import net.harupiza.hikouki.aircraft.parts.Seat;
import org.bukkit.Bukkit;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class Aircraft {
    public final ArrayList<SeparatedPart<? extends PartBasic>> child = new ArrayList<>();

    // 機体
    @Nonnull
    public final CustomModel main;
    // ランディングギア
    @Nonnull
    public final SeparatedPart<CustomModel> landingGear;
    // 操縦士の席
    @Nonnull
    public final SeparatedPart<Seat> pilotSeat;
    // 副操縦士の席
    @Nullable
    public final SeparatedPart<Seat> coPilotSeat;

    // 副操縦士が動かす時はtrue
    public boolean coPilotMode = false;
    // ギアダウン時はtrue
    public boolean isGearDown = true;

    public Aircraft (@Nonnull CustomModel main, @Nonnull SeparatedPart<CustomModel> landingGear, @Nonnull SeparatedPart<Seat> pilotSeat, @Nullable SeparatedPart<Seat> coPilotSeat) {
        this.main = main;
        this.landingGear = landingGear;
        this.pilotSeat = pilotSeat;
        this.coPilotSeat = coPilotSeat;

        child.addAll(initParts());

        // 全ての座標を相対的にします。
        positionInitialize();

        Bukkit.getScheduler().runTaskTimer(Hikouki.getInstance(), t -> onTick(), 0, 0);
    }

    // 全ての座標を相対的にします。
    void positionInitialize() {
        final var mainLoc = main.itemDisplay.getLocation();
        landingGear.setAsVector3d(landingGear.part.itemDisplay.getLocation().subtract(mainLoc).toVector().toVector3d());
        pilotSeat.setAsVector3d(pilotSeat.part.itemDisplay.getLocation().subtract(mainLoc).toVector().toVector3d());
        if (coPilotSeat != null) coPilotSeat.setAsVector3d(coPilotSeat.part.itemDisplay.getLocation().subtract(mainLoc).toVector().toVector3d());
    }

    // 座標を更新します
    void positionUpdate() {
        final var mainLoc = main.itemDisplay.getLocation();
        final var landingGearEntity = landingGear.part.itemDisplay;
        landingGearEntity.setTransformation(new Transformation(
                new Vector3f((float) (mainLoc.x() + landingGear.x),
                        (float) (mainLoc.y() + landingGear.y), (float) (mainLoc.z() + landingGear.z)),
                landingGearEntity.getTransformation().getLeftRotation(),
                new Vector3f((float) main.scaleX, (float) main.scaleY, (float) main.scaleZ),
                landingGearEntity.getTransformation().getRightRotation()
        ));
        final var pilotSeatEntity = pilotSeat.part.itemDisplay;
        pilotSeatEntity.setTransformation(new Transformation(
                new Vector3f((float) (mainLoc.x() + pilotSeat.x),
                        (float) (mainLoc.y() + pilotSeat.y), (float) (mainLoc.z() + pilotSeat.z)),
                pilotSeatEntity.getTransformation().getLeftRotation(),
                new Vector3f((float) main.scaleX, (float) main.scaleY, (float) main.scaleZ),
                pilotSeatEntity.getTransformation().getRightRotation()
        ));
        if (coPilotSeat != null) {
            final var coPilotSeatEntity = coPilotSeat.part.itemDisplay;
            coPilotSeatEntity.setTransformation(new Transformation(
                    new Vector3f((float) (mainLoc.x() + coPilotSeat.x),
                            (float) (mainLoc.y() + coPilotSeat.y), (float) (mainLoc.z() + coPilotSeat.z)),
                    coPilotSeatEntity.getTransformation().getLeftRotation(),
                    new Vector3f((float) main.scaleX, (float) main.scaleY, (float) main.scaleZ),
                    coPilotSeatEntity.getTransformation().getRightRotation()
            ));
        }
    }

    // 毎ティック呼ばれる
    void onTick() {
        main.onTickEarly();
        landingGear.part.onTickEarly();
        pilotSeat.part.onTickEarly();
        if (coPilotSeat != null) coPilotSeat.part.onTickEarly();
        for (var part : child) {
            part.part.onTickEarly();
        }

        positionUpdate();

        main.onTick();
        landingGear.part.onTick();
        pilotSeat.part.onTick();
        if (coPilotSeat != null) coPilotSeat.part.onTick();
        for (var part : child) {
            part.part.onTick();
        }
    }

    abstract public ArrayList<SeparatedPart<? extends PartBasic>> initParts();
}
