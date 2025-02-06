package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity basketBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        RoadRunnerBotEntity chaimberBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


        basketBot.runAction(basketBot.getDrive().actionBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                        .strafeToLinearHeading(new Vector2d(-59,-55), Math.toRadians(230))
                        .strafeToLinearHeading(new Vector2d(-51,-38), Math.toRadians(90))
                        .strafeToLinearHeading(new Vector2d(-59,-55), Math.toRadians(230))
                        .setTangent(45)
                        .splineToLinearHeading(new Pose2d(-23,-4, Math.toRadians(0)), Math.toRadians(0))


                .build());

        chaimberBot.runAction(chaimberBot.getDrive().actionBuilder(new Pose2d(0, -70, Math.toRadians(90)))
                        .strafeTo(new Vector2d(0,-35))
                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(basketBot)
                .addEntity(chaimberBot)
                .start();
    }
}