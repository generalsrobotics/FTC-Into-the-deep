package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, -60, Math.toRadians(270)))
                        .setTangent(Math.toRadians(135))
                        .lineToXLinearHeading(-50, Math.toRadians(230))
                        .setTangent(Math.toRadians(45))
                        .lineToY(-55)
                        .setTangent(Math.toRadians(45))
                        .splineToLinearHeading(new Pose2d(-51,-38,Math.toRadians(90)), Math.toRadians(90))
                        .setReversed(true)
                        .setTangent(225)
                        .splineToLinearHeading(new Pose2d(-59,-55,Math.toRadians(230)), Math.toRadians(225))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}