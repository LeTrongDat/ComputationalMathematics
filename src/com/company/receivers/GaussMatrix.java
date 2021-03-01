package com.company.receivers;

import com.company.enums.StreamOption;

import java.math.BigDecimal;
import java.util.List;

/**
 * Gauss matrix class provides the set of methods to compute determinants, residuals and solution of linear systems.
 */
public interface GaussMatrix {
    /**
     * Input the matrix from a file or a command line interface up to streamOpt param.
     * @param streamOpt
     */
    void read(StreamOption streamOpt);

    /**
     * Output the matrix to a file or a command line interface up to streamOpt param.
     * @param streamOpt
     */
    void write(StreamOption streamOpt);

    /**
     * Generate random matrix
     */
    void seedMatrix(int n);

    /**
     * @return original matrix
     */
    BigDecimal[][] getOriginalMatrix();

    /**
     * Reduce the matrix to triangular form.
     * @return triangular form
     */
    BigDecimal[][] toTriangular();

    /**
     * Compute matrix's determinants.
     * @return determinant
     */
    BigDecimal computeDet();

    /**
     * Compute solution of linear system represented in matrix form.
     * @return solution of the linear system
     */
    List<BigDecimal> computeSol();

    /**
     * Compute residual of solution of linear system representd in matrix form.
     * @return residual of the linear system
     */
    List<BigDecimal> computeRes();
}
