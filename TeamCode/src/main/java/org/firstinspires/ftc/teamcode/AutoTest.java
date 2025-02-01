package org.firstinspires.ftc.teamcode;
//package org.firstinspires.ftc.teamcode.teleops;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
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
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")
public  class AutoTest extends LinearOpMode {

    public static double lineToBar = -35;

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        ExtensionA extention = new ExtensionA(hardwareMap);
        ElevatorA elevator = new ElevatorA(hardwareMap);
        ClawA claw = new ClawA(hardwareMap);
        BicepA bicep = new BicepA(hardwareMap);

        Servo ext = hardwareMap.get(Servo.class, "ext");
        DcMotor elevatorLeft = hardwareMap.get(DcMotor.class, "elevatorLeft");




        /*Trajectory myTrajectory = drive.trajectoryBuilder(initialPose)
                .strafeRight(10)
                .forward(5)
                .build();
*/
        claw.initClaw();
        bicep.init();
        waitForStart();


        if(isStopRequested()) return;

        Actions.runBlocking(
                drive.actionBuilder(initialPose)

                        .afterTime(0, extention.extendOut())
                        .lineToY(lineToBar)
                        .afterTime(0, extention.extendIn())
                        .waitSeconds(5)

                       // .lineToY(-60)
//                        .setTangent(Math.toRadians(0))
//                        .splineToConstantHeading(new Vector2d(36,-23), Math.toRadians(90))
//                        .splineToConstantHeading(new Vector2d(47,-5),Math.toRadians(90))
//                        .splineToConstantHeading(new Vector2d(47,-50),Math.toRadians(90))
                        .build());


        telemetry.update();
    }
}