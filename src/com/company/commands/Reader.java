package com.company.commands;

import com.company.receivers.GaussMatrix;
import com.company.enums.StreamOption;

public class Reader implements Command {
    private GaussMatrix matrix;
    private StreamOption opt;

    public Reader(GaussMatrix matrix, StreamOption opt) {
        this.matrix = matrix;
        this.opt = opt;
    }
    @Override
    public void execute() {
        this.matrix.read(opt);
    }
}
