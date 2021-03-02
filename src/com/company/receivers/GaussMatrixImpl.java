package com.company.receivers;

import com.company.enums.StreamOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GaussMatrixImpl implements GaussMatrix {
    private int size;
    private BigDecimal[][] matrix;
    private BigDecimal det = new BigDecimal(0);
    private final int COMPUTATIONAL_SCALE = 20; // Error when performing calculation
    private final int RESULT_SCALE = 3; // Error when printing out the result

    @Override
    public void read(StreamOption streamOpt) {
        Scanner sc = new Scanner(System.in);
        try {
            switch (streamOpt) {
                case FILE -> {
                    System.out.print("> Please enter your file's name: ");
                    File file = new File(sc.next());
                    sc = new Scanner(file);
                }
                case CLI -> System.out.println("> Please enter your matrix: ");
                default -> throw new UnexpectedException("Can not parse input option");
            }
        } catch (FileNotFoundException e) {
            System.out.println("> File not found.");
            System.exit(0);
        } catch (UnexpectedException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        this.size = sc.nextInt();
        matrix = new BigDecimal[this.size][this.size+1];
        for(int row = 0; row < this.size; ++row) {
            for(int col = 0; col < this.size + 1; ++col) {
                String val = sc.next();
                val = val.replace(",", ".");
                this.matrix[row][col] = new BigDecimal(val);
            }
        }
    }

    @Override
    public void write(StreamOption streamOpt) {
    }

    @Override
    public void seedMatrix(int n) {
        this.size = n;
        this.matrix = new BigDecimal[this.size][this.size+1];
        for(int row = 0; row < this.size; ++row)
            for(int col = 0; col < this.size + 1; ++col)
                this.matrix[row][col] = BigDecimal.valueOf(Math.random() * 1e3).setScale(RESULT_SCALE, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal[][] getOriginalMatrix() {
        return this.matrix;
    }

    @Override
    public BigDecimal[][] toTriangular() {
        BigDecimal[][] matrix = this.matrix;
        int swapCount = 0;
        int pivotCol = 0, pivotRow = 0;
        this.det = BigDecimal.ONE;
        while (pivotCol < this.size && pivotRow < this.size) {
            int i = pivotRow;
            for(; i < this.size; ++i) if (matrix[i][pivotCol].compareTo(BigDecimal.ZERO) != 0) break;
            if (i >= this.size) {
                pivotCol ++;
                continue;
            }
            swapRow(pivotRow, i);
            swapCount = 1 - swapCount;
            for(int row = pivotRow + 1; row < this.size; ++row) {
                BigDecimal coefficient = matrix[row][pivotCol].divide(matrix[pivotRow][pivotCol], COMPUTATIONAL_SCALE, RoundingMode.HALF_UP);
                matrix[row][pivotCol] = BigDecimal.ZERO;
                for(int col = pivotCol + 1; col < this.size + 1; ++col)
                    matrix[row][col] = matrix[row][col].subtract(matrix[pivotRow][col].multiply(coefficient)).setScale(COMPUTATIONAL_SCALE, RoundingMode.HALF_UP);
            }
            pivotRow ++;
            pivotCol ++;
        }
        for(int i = 0; i < this.size; ++i)
            this.det = this.det.multiply(matrix[i][i]);
        this.det = this.det
                .multiply(swapCount == 0 ? BigDecimal.ONE : BigDecimal.valueOf(-1));
        return this.matrix;
    }

    @Override
    public BigDecimal computeDet() {
        return this.det;
    }

    @Override
    public List<BigDecimal> computeSol() {
        List<BigDecimal> solution = new ArrayList<>();
        BigDecimal[][] matrix = toTriangular();
        for(int i = this.size - 1; i >= 0; --i) {
            if (matrix[i][i].compareTo(BigDecimal.ZERO) == 0) return null;
            BigDecimal tot = matrix[i][this.size];
            for(int col = this.size - 1; col > i; --col)
                tot = tot.subtract(solution.get(this.size - 1 - col).multiply(matrix[i][col]));
            solution.add(tot.divide(matrix[i][i], RESULT_SCALE, RoundingMode.HALF_UP));
        }
        Collections.reverse(solution);
        return solution;
    }

    @Override
    public List<BigDecimal> computeRes() {
        BigDecimal[][] matrix = toTriangular();
        List<BigDecimal> solution = computeSol();
        List<BigDecimal> residual = new ArrayList<>();
        for(int row = 0; row < this.size; ++row) {
            BigDecimal tot = new BigDecimal(0);
            for(int col = 0; col < this.size; ++col)
                tot = tot.add(solution.get(col).multiply(matrix[row][col]));
            residual.add(matrix[row][this.size].subtract(tot).abs().setScale(RESULT_SCALE, RoundingMode.HALF_UP));
        }
        return residual;
    }

    // private methods
    private void swapRow(int r1, int r2) {
        for(int i = 0; i < this.size; ++i) {
            BigDecimal tmp = matrix[r1][i];
            matrix[r1][i] = matrix[r2][i];
            matrix[r2][i] = tmp;
        }
    }
}
