package com.company;

import com.company.commands.*;
import com.company.enums.StreamOption;
import com.company.receivers.GaussMatrix;
import com.company.receivers.GaussMatrixImpl;

import java.util.*;

public class Invoker {
    public void start(String[] args) {
        System.out.println("> Welcome to the gauss world!");
        GaussMatrix matrix = new GaussMatrixImpl();

        Set<Character> opts = new HashSet<>();
        for(String arg: args) {
            if (arg.charAt(0) != '-') continue;
            for(int i = 1; i < arg.length(); ++i)
                opts.add(arg.charAt(i));
        }
        if (opts.contains('h')) help();

        StreamOption streamOpt = StreamOption.CLI;
        if (opts.contains('o')) streamOpt = StreamOption.FILE;

        Command reader = new Reader(matrix, streamOpt);
        Command generator = new Generator(matrix);
        Command orgMatrixGetter = new OrgMatrixGetter(matrix);
        Command echelonConverter = new EchelonConverter(matrix);
        Command detComputation = new DetComputation(matrix);
        Command resComputation = new ResComputation(matrix);
        Command solComputation = new SolComputation(matrix);


        if (opts.contains('r'))
            generator.execute();
        else
            reader.execute();

        orgMatrixGetter.execute();
        echelonConverter.execute();
        detComputation.execute();
        solComputation.execute();
        resComputation.execute();
    }

    private void help() {
        System.out.println("""
                > Usage: [options]
                > Options:
                > -h           Print app command line options
                > -o           Read matrix from file
                > -r           Auto generate random matrix""");
    }
}
