package com.company.commands;

import com.company.receivers.GaussMatrix;

public class Generator implements Command {
    private GaussMatrix matrix;
    public Generator(GaussMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        this.matrix.seedMatrix();
    }
}
