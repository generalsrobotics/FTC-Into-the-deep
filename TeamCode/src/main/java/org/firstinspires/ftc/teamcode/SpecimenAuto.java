package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Actions.BicepA;
import org.firstinspires.ftc.teamcode.Actions.ClawA;
import org.firstinspires.ftc.teamcode.Actions.ElevatorA;
import org.firstinspires.ftc.teamcode.Actions.ExtensionA;
@Autonomous(name = "SpecimenAutonomous", group = "Autonomous")
public class SpecimenAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(-36, -60, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        ExtensionA extention = new ExtensionA(hardwareMap);
        ElevatorA elevator = new ElevatorA(hardwareMap);
        ClawA claw = new ClawA(hardwareMap);
        BicepA bicep = new BicepA(hardwareMap);

        Servo ext = hardwareMap.get(Servo.class, "ext");
        DcMotor elevatorLeft = hardwareMap.get(DcMotor.class, "elevatorLeft");



        waitForStart();

        if(isStopRequested()) return;


        Actions.runBlocking(
                new ParallelAction(
                        elevator.MoveArm(),
                        drive.actionBuilder(initialPose)
                                .afterTime(0, extention.extendOut())
                                .strafeTo(new Vector2d(0,-35))
                                .afterTime(0, extention.extendIn())
                                .waitSeconds(5)
                                .build()));


    }


}
