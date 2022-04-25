package com.example.tetrisnoactivity.models;

import android.graphics.Color;
import android.graphics.Point;
import com.example.tetrisnoactivity.constants.FieldConstants;

import androidx.annotation.NonNull;

import java.util.Random;

public class Block {
    private int shapeIndex;
    private int frameNumber;
    private BlockColor color = BlockColor.PINK;
    private Point position;

    private Block(int shapeIndex, BlockColor blockColor) {
        this.frameNumber = 0;
        this.shapeIndex = shapeIndex;
        //this.color = blockColor;
        this.position = new Point (FieldConstants.COLUMN_COUNT.getValue()/2, 0);
    }

    public static Block createBlock() {
        Random random = new Random();
        int shapeIndex = random.nextInt(Shape.values().length);
        BlockColor blockColor = BlockColor.values()
            [random.nextInt(BlockColor.values().length)];

        Block block = new Block(shapeIndex, blockColor);
        block.position.x -= Shape.values()
            [shapeIndex].getStartPosition();
        return block;
    }

    public static int getColor(byte value) {
        for (BlockColor color : BlockColor.values()){
            if (value == color.byteValue) {
                    return color.rgbValue;
            }
        }
        return -1;
    }
    public final void setState(int frame, Point position) {
        this.frameNumber = frame;
        this.position = position;
    }

    @NonNull
        public final byte[][] getShape(int frameNumber) {
        return Shape.values()[shapeIndex].getFrame(frameNumber).as2dByteArray();
    }

    public Point getPosition() {
        return this.position;
    }

    public final int getFrameCount() {
        return Shape.values()[shapeIndex].getFrameCount();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getColor() {
        return color.rgbValue;
    }

    public byte getStaticValue() {
        return (byte) color.byteValue;
    }


    public enum BlockColor {

        PINK(Color.rgb(247, 134, 134), (byte) 2),
        GREEN(Color.rgb(179, 247, 134), (byte) 3),
        ORANGE(Color.rgb(247, 181, 134), (byte) 4),
        YELLOW(Color.rgb(247, 240, 134), (byte) 5),
        CYAN(Color.rgb(134, 247, 192), (byte) 6);
        BlockColor(int rgbValue, byte value) {
            this.rgbValue = rgbValue;
            this.byteValue = value;
        }
        final int rgbValue;
        final int byteValue;


    }
}
