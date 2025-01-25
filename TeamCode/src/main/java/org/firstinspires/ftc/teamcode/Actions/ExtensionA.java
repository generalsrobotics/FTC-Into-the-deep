package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ExtensionA {
private Servo ext;
private Servo ext01;

double extPosition;
double initPos = 0.34;

public  ExtensionA (HardwareMap hm){
    ext = hm.get(Servo.class, "ext");
    ext01 = hm.get(Servo.class, "ext01");
}


    public class ExtendOut implements Action {



        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            ext.setPosition(1-0.04);
            ext01.setPosition(0.04);

            packet.put("ext Post out", ext.getPosition());
            return false;

        }
    }

    public class ExtendIn implements Action {
        private boolean initialized = false;



        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
                ext.setPosition(1-.34);
                ext01.setPosition(.34);

            packet.put("ext Post in", ext.getPosition());
            return false;
        }
    }

    public Action extendOut(){
        return new  ExtendOut();
    }
    public Action extendIn(){
        return new ExtendIn();
    }
}
