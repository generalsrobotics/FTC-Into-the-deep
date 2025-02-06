package org.firstinspires.ftc.teamcode;
//package org.firstinspires.ftc.teamcode.teleops;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Actions.BicepA;
import org.firstinspires.ftc.teamcode.Actions.ClawA;
import org.firstinspires.ftc.teamcode.Actions.ElevatorA;
import org.firstinspires.ftc.teamcode.Actions.ExtensionA;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "BasketAutonomous", group = "Autonomous")
public  class BasketAuto extends LinearOpMode {
    public static int highBasketPos = 2700;
    public static double scoreHigh = .55;
    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-36, -60, Math.toRadians(270));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        ExtensionA extention = new ExtensionA(hardwareMap);
        ElevatorA elevator = new ElevatorA(hardwareMap);
        ClawA claw = new ClawA(hardwareMap);
        BicepA bicep = new BicepA(hardwareMap);

        Servo ext = hardwareMap.get(Servo.class, "ext");
        DcMotor elevatorLeft = hardwareMap.get(DcMotor.class, "elevatorLeft");
        BicepA.scorePos = scoreHigh;

        claw.initClaw();
        bicep.init();


        Action dropInBasket = new SequentialAction(
                new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                return elevatorLeft.getCurrentPosition() <= highBasketPos - 100;
            }},
                bicep.bicepScoreHigh(),
                new SleepAction(.5),
                claw.openClaw(),
                bicep.bicepUp(),
                new SleepAction(1));

        Action collectSample = new SequentialAction(
                claw.openClaw(),
                bicep.bicepDown(),
                bicep.bicepInit());


        waitForStart();


        if(isStopRequested()) return;

        Actions.runBlocking(
            new ParallelAction(
                    elevator.MoveArm(),

                    drive.actionBuilder(initialPose)
                    .setTangent(Math.toRadians(135))
                    .afterTime(0, elevator.MoveArm())
                    .afterTime(0, elevator.setTargetPos(highBasketPos))
                    .lineToXLinearHeading(-50, Math.toRadians(220))
                    .setTangent(Math.toRadians(230))
                    .lineToY(-55)
                    .stopAndAdd(dropInBasket)
                    .afterTime(0, elevator.setTargetPos(10))
                    .setTangent(Math.toRadians(45))
                    .splineToLinearHeading(new Pose2d(-51,-38,Math.toRadians(90)), Math.toRadians(90))
                    .afterTime(1,collectSample)
                    .afterTime(0, elevator.setTargetPos(highBasketPos))
                    .setReversed(true)
                    .setTangent(225)
                    .splineToLinearHeading(new Pose2d(-59,-55,Math.toRadians(230)), Math.toRadians(225))
                    .stopAndAdd(dropInBasket)
                    .build()));



        telemetry.update();
    }
}