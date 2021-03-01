package com.company.commands;

import com.company.receivers.GaussMatrix;

import java.util.Scanner;

public class Generator implements Command {
    private GaussMatrix matrix;
    public Generator(GaussMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        System.out.print("Please enter the size of random matrix: ");
        Scanner sc = new Scanner(System.in);
        this.matrix.seedMatrix(sc.nextInt());
    }
}
