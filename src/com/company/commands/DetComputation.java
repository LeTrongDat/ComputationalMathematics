package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.math.BigDecimal;

public class DetComputation implements Command {
    private GaussMatrix matrix;
    public DetComputation(GaussMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        BigDecimal determinant = this.matrix.computeDet();
        System.out.println("> A determinant of the matrix is: " + determinant.toString());
    }
}
