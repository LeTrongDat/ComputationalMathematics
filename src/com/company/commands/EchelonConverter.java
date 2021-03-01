package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EchelonConverter implements Command {
    private final int RESULT_SCALE = 3;
    private GaussMatrix matrix;
    public EchelonConverter(GaussMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        BigDecimal[][] triangularForm = this.matrix.toTriangular();
        System.out.println("> A triangular form of the matrix is: ");
        for(int row = 0; row < triangularForm.length; ++row) {
            System.out.print("> ");
            for(int col = 0; col < triangularForm[row].length; ++ col)
                System.out.printf("%10s ", triangularForm[row][col].setScale(RESULT_SCALE, RoundingMode.HALF_UP));
            System.out.println();
        }
    }
}
