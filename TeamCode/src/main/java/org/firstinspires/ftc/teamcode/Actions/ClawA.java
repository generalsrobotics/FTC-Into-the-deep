package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawA {
    public Servo clawAngle, claw;
    public double angle, clawPosition;
    public final double initPos = .82;

    public final double midPosition = .76;
    public final double openPosition = .6;

    public  ClawA(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class, "claw");
        clawAngle = hwMap.get(Servo.class, "clawAngle");
    }


    public class CloseClaw implements Action {


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(initPos);
            return false;
        }
    }

    public class OpenClaw implements Action {


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(openPosition);
            return false;
        }
    }


    public Action openClaw() { // open claw action
        return new OpenClaw();
    }

    public Action closeClaw(){
        return new CloseClaw();
    }

    public void initClaw(){
        claw.setPosition(initPos);
    }



}
