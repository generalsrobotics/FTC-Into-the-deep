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

import org.firstinspires.ftc.teamcode.Actions.ElevatorA;
import org.firstinspires.ftc.teamcode.Actions.ExtensionA;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_PIXEL", group = "Autonomous")
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        ExtensionA extention = new ExtensionA(hardwareMap);
        ElevatorA elevator = new ElevatorA(hardwareMap);


        Servo ext = hardwareMap.get(Servo.class, "ext");
        DcMotor elevatorLeft = hardwareMap.get(DcMotor.class, "elevatorLeft");



        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                /*.lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(48)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3)

                 */
                .strafeTo(new Vector2d(8, 20))
                .turn(Math.toRadians(45))
                //.setTangent(Math.toRadians(-45))
                //.lineToY(5)
                ;



        /*Trajectory myTrajectory = drive.trajectoryBuilder(initialPose)
                .strafeRight(10)
                .forward(5)
                .build();
*/
        telemetry.addLine("init");




        Actions.runBlocking( new ParallelAction( elevator.moveArm(),  new SequentialAction(extention.extendOut(), new SleepAction(2), extention.extendIn())));


       telemetry.update();
        waitForStart();


        if(isStopRequested()) return;

        //drive.followTrajectory(tab1);

        telemetry.update();
    }
}