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
        System.out.println("> A solution of the linear system is: " + solution.toString());
    }
}
