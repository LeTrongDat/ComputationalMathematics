package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrgMatrixGetter implements Command {
    private GaussMatrix matrix;
    public OrgMatrixGetter(GaussMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        BigDecimal[][] matrix = this.matrix.getOriginalMatrix();
        System.out.println("> Original matrix: ");
        for(int row = 0; row < matrix.length; ++row) {
            System.out.print("> ");
            for(int col = 0; col < matrix[row].length; ++ col)
                System.out.printf("%10s ", matrix[row][col]);
            System.out.println();
        }
    }
}
