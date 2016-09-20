# Shamir
Java program for Shamir's Secret Sharing Scheme.  This scheme takes a secret and breaks it into t shares, n of which are needed in order to reconstruct the shares.
This implementation uses GF(p) for the polynomial.
## Usage
Navigate to the jar directory out/artifacts/Shamir_jar


java -jar Shamir.jar share <path to file containing secret> <number of shares> <threshold>

java -jar Shamir.jar combine <path to file containing shares> <prime>
