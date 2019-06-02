package com.michaelho.DivideAndConquer;

import org.junit.Test;

import static org.junit.Assert.*;

public class DCTests {
    DC1 dc1 = new DC1();

    @Test
    public void testMultiplication1() {
        int input1 = 123;
        int input2 = 456;
        int expected = 56088;
        assertEquals(expected, dc1.multiplication.calculateByNaiveApproach(input1, input2));
        assertEquals(expected, dc1.multiplication.calculateByFastApproach(input1, input2));
    }

    @Test
    public void testMultiplication2() {
        int input1 = 712;
        int input2 = 45;
        int expected = 32040;
        assertEquals(expected, dc1.multiplication.calculateByNaiveApproach(input1, input2));
        assertEquals(expected, dc1.multiplication.calculateByFastApproach(input1, input2));
    }
}
