package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.math.BigDecimal;
import java.util.List;

public class SolComputation implements Command {
    private GaussMatrix matrix;
    public SolComputation(GaussMatrix matrix) {
        this.matrix = matrix;
    }
    @Override
    public void execute() {
        List<BigDecimal> solution = this.matrix.computeSol();
        if (solution == null) {
            System.out.println("> The linear system hasn't any solutions or has infinite solutions");
            System.exit(0);
        }
        System.out.println("> A solution of the linear system is: " + solution.toString());
    }
}
