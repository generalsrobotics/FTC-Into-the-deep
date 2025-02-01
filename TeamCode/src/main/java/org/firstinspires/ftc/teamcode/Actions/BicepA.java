package org.firstinspires.ftc.teamcode.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BicepA {
    public Servo bicepLeft, bicepRight;
    public static final double bicepPositionDown = .93;
    public static final double bicepPositionUp = .53;
    public static final double initPos = .45;
    public static double scorePos = .55;



    public BicepA(HardwareMap hwMap) {
        bicepLeft = hwMap.get(Servo.class, "sv1");
        bicepRight = hwMap.get(Servo.class, "sv2");
    }

    public class BicepDown implements Action {


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bicepLeft.setPosition(1 - bicepPositionDown);
            bicepRight.setPosition(bicepPositionDown);
            return false;
        }
    }

    public class BicepUp implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bicepLeft.setPosition(1 - bicepPositionUp);
            bicepRight.setPosition(bicepPositionUp);
            return false;
        }
    }

    public class BicepScoreHigh implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bicepLeft.setPosition(1 - scorePos);
            bicepRight.setPosition(scorePos);
            return false;
        }
    }



    public Action bicepUp() {
        return new BicepUp();

    }

    public Action bicepDown(){
        return new BicepDown();
    }
    public Action bicepScoreHigh(){
        return new BicepScoreHigh();
    }

    public void init(){
        bicepLeft.setPosition(1-initPos);
        bicepRight.setPosition(initPos);
    }

    public Action bicepInit(){
        return new Action(){

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                bicepLeft.setPosition(1-initPos);
                bicepRight.setPosition(initPos);
                return false;
            }
        };
    }
}

//
//public void init()
//{
//    bicepLeft = hdwMap.get(Servo.class, "sv1");
//    bicepRight = hdwMap.get(Servo.class, "sv2");
//
//
//
//
//       /* leftFrontDrive  = hdwMap.get(DcMotor.class, "elevatorRight");
//
//        RightFrontDrive  = hdwMap.get(DcMotor.class, "elevatorLeft");
//
//        encoDer = leftFrontDrive.getCurrentPosition();
//
//        */
//    bicepLeft.setPosition(1-bicepPosition);
//    bicepRight.setPosition(bicepPosition);
//
//}
//
//public void moveServoToPosition()
//{
//
//}
//
//
//
//public void update(Gamepad gamepad2)
//{
//
//
//    if(gamepad2.right_bumper)
//    {
//        bicepPosition =.53;// Math.max(0.53,bicepPosition-0.00001);
//
//        //  bicepPosition = Math.max(0.53, bicepPosition - 0.0000001);
//        //bicepPosition =.53;
//        bicepLeft.setPosition(1-bicepPosition);
//        bicepRight.setPosition(bicepPosition);
//
//    }
//
//    if(gamepad2.left_bumper)
//    {
//        bicepPosition =.85;// Math.min(.9,bicepPosition+0.000001);
//        // bicepPosition = Math.min(0.7, bicepPosition+0.0000001);
//        bicepLeft.setPosition(1-bicepPosition);
//        bicepRight.setPosition(bicepPosition);
//
//    }
//
//          /*
//        if( gamepad2.a && bicepPosition !=.93)
//        {
//
//                bicepPosition =.93;// Math.min(.9,bicepPosition+0.000001);
//               // bicepPosition = Math.min(0.7, bicepPosition+0.0000001);
//                bicepLeft.setPosition(1-bicepPosition);
//                bicepRight.setPosition(bicepPosition);
//
//         }else if(gamepad2.a)
//         {
//                bicepPosition =.85;// Math.min(.9,bicepPosition+0.000001);
//               // bicepPosition = Math.min(0.7, bicepPosition+0.0000001);
//                bicepLeft.setPosition(1-bicepPosition);
//                bicepRight.setPosition(bicepPosition);
//
//         }
//         */
//    if(gamepad2.a && !changed)
//    {
//        if(bicepPosition == .85)
//        {
//            bicepPosition =.93;
//            bicepLeft.setPosition(1-bicepPosition);
//            bicepRight.setPosition(bicepPosition);
//
//        }
//        else {
//            bicepPosition = .85;
//            bicepLeft.setPosition(1-bicepPosition);
//            bicepRight.setPosition(bicepPosition);
//        }
//        changed = true;
//
//    } else if(!gamepad2.a) changed = false;
//
//
//    // bicepPosition =.9;
//
//
//    // bicepPosition = Range.clip(bicepPosition, 0.53, .8);
//
//
//
//
//
//                   /* bicepPosition += -servodelta;
//                    bicepPosition = Range.clip(bicepPosition, 0.53, .8);
//
//                   // marker.setPosition(bicepPosition);
//
//                    bicepLeft.setPosition(1-bicepPosition);
//                    bicepRight.setPosition(bicepPosition);
//
//
//                   */
//
//
//
//