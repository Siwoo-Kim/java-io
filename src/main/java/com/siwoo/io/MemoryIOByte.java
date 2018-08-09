package com.siwoo.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemoryIOByte {

    @Test
    public void bytesInMemory() {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] outSrc = null;

        try(ByteArrayInputStream in = new ByteArrayInputStream(inSrc);
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int data = 0;
            while ((data=in.read()) != -1) {
                out.write(data);
            }
            outSrc = out.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(outSrc));
        assertArrayEquals(outSrc, inSrc);

        outSrc = null;
        byte[] temp = new byte[inSrc.length];

        try(ByteArrayInputStream in = new ByteArrayInputStream(inSrc);
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            in.read(temp,0,inSrc.length);
            out.write(temp, 5, 5);
            outSrc = out.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        }

        assertArrayEquals(Arrays.copyOfRange(inSrc,5,inSrc.length), outSrc);
        System.out.println(Arrays.toString(outSrc));
    }

    @Test
    public void available() {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        try(ByteArrayInputStream in = new ByteArrayInputStream(inSrc);
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            while (in.available() > 0) {
                int length = in.read(temp);
                out.write(temp, 0, length);
                outSrc = out.toByteArray();
                System.out.println(Arrays.toString(outSrc));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
