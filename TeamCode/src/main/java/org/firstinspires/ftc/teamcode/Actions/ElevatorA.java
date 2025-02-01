/*
Copyright 2024 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode.Actions;

import android.provider.Settings;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains a minimal example of a Linear "OpMode". An OpMode is a 'program' that runs
 * in either the autonomous or the TeleOp period of an FTC match. The names of OpModes appear on
 * the menu of the FTC Driver Station. When an selection is made from the menu, the corresponding
 * OpMode class is instantiated on the Robot Controller and executed.
 *
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */

public class ElevatorA {
    private HardwareMap hdwMap;
     DcMotor elevatorLeft;
    private DcMotor elevatorRight;

    private int targetPosition = 0;
     boolean init = false;
     boolean reached = true;


    // PID Vars
    private double Kp = .0015;
    private double Ki = 0;
    private double Kd = 0;
    private double Lefterror, LeftlastError, LeftintegralSum, LeftencoDer = 0;
    private double reference = 0;
    //int encoDer=0;

    private double Righterror, RightlastError, RightintegralSum, RightencoDer = 0;

    private double leftencoDer;
    private double rightencoDer;


    public ElevatorA(HardwareMap hdwMap) {
        this.hdwMap = hdwMap;

        elevatorLeft = hdwMap.dcMotor.get("elevatorLeft");
        elevatorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevatorRight = hdwMap.dcMotor.get("elevatorRight");
        elevatorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorRight.setDirection(DcMotor.Direction.REVERSE);


        leftencoDer = elevatorLeft.getCurrentPosition();


        rightencoDer = elevatorRight.getCurrentPosition();


    }




    public class MoveElevatoDynamicly implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            updateArmLocation(targetPosition);
            return true;
        }
    }

    public  class SetTargetPosition implements  Action{
        int target;
        public SetTargetPosition(int targetPosition){
            this.target = targetPosition;

        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            setTargetPosition(target);
            return false;

        }

    }
    public void updateArmLocation(double reference) {

        this.reference = reference;
        ElapsedTime timer = new ElapsedTime();



              /*  if(gamepad1.x)
                    reference=0;
                if(gamepad1.y)
                    reference =1900;
                if(gamepad1.b)
                    reference =4000;
*/


        double encoderLeftPosition = elevatorLeft.getCurrentPosition() - LeftencoDer;
        Lefterror = reference - encoderLeftPosition;
        // rate of change of the error
        double LEFTderivative = (Lefterror - LeftlastError) / timer.seconds();
        // sum of all error over time
        LeftintegralSum = LeftintegralSum + (Lefterror * timer.seconds());
        double Leftout = (Kp * Lefterror) + (Ki * LeftintegralSum) + (Kd * LEFTderivative);
        elevatorLeft.setPower(Leftout);


        //right motor
        //   /*
        double encoderRightPosition = elevatorRight.getCurrentPosition() - RightencoDer;
        Righterror = reference - encoderRightPosition;
        double Rightderivative = (Righterror - RightlastError) / timer.seconds();
        RightintegralSum = RightintegralSum + (Righterror * timer.seconds());
        double Rightout = (Kp * Righterror) + (Ki * RightintegralSum) + (Kd * Rightderivative);
        //*/
        elevatorRight.setPower(Rightout);


        // elevatorRight.setPower(-Leftout);


        LeftlastError = Lefterror;

        RightlastError = Righterror;

        // reset the timer for next time
        timer.reset();

        //   return ""+encoderRightPosition;

    }

    public Action MoveArm(){
        return new MoveElevatoDynamicly();
    }
    public Action setTargetPos(int targetPosition){
        return new SetTargetPosition(targetPosition);
    }

    public  void setTargetPosition(int targetPosition){
        this.targetPosition = targetPosition;
    }


}


    

