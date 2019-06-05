package com.michaelho.DataObjects;

public class Complex {

    private double real;
    private double imaginary;

    /**
     * The constructor of a complex number.
     *
     * @param real The real part of the complex number.
     * @param imaginary The imaginary part of the complex number.
     * */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    //  sum of two complex numbers
    public Complex add(Complex c) {
        return new Complex(real+c.real, imaginary+c.imaginary);
    }

    // difference of two complex numbers
    public Complex subtract(Complex c) {
        return new Complex(real-c.real, imaginary-c.imaginary);
    }

    /**
     * Calculate the product of 2 complex number.
     *
     * @param c The complex number to multiply.
     *
     * @return the result of calculation as a complex number.
     * */
    public Complex multiply(Complex c) {
        return new Complex(real*c.real - imaginary*c.imaginary, real*c.imaginary + imaginary*c.real);
    }

    public Complex multiply3(Complex c){
        double ac = real*c.real;
        double bd = imaginary*c.imaginary;
        return new Complex(ac-bd,((real+imaginary)*(c.real+c.imaginary))-ac-bd);
    }

    // get the real part of complex number
    public double getReal() {
        return real;
    }

    /**
     * Convert complex number to a string.
     * */
    public String toString(){
        return String.format("%8.4f + %8.4fi", real, imaginary);
    }
}
