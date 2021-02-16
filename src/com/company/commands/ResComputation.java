package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.math.BigDecimal;
import java.util.List;

public class ResComputation implements Command {
    private GaussMatrix matrix;
    public ResComputation(GaussMatrix matrix) {
        this.matrix = matrix;
    }
    @Override
    public void execute() {
        List<BigDecimal> residual = this.matrix.computeRes();
        System.out.println("> A residual of the linear system is: " + residual.toString());
    }
}
