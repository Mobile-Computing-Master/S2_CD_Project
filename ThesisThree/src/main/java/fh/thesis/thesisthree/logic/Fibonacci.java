package fh.thesis.thesisthree.logic;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("Fibonacci")
public class Fibonacci {

    public BigInteger calculate(int count) {
        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;
        BigInteger n3 = BigInteger.ZERO;

        for (int i = 2; i <= count; ++i) {
            n3 = n1.add(n2);
            n1 = n2;
            n2 = n3;
        }

        return n3;
    }
}
