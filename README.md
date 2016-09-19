# Shamir
Java program for Shamir's Secret Sharing Scheme.  This scheme takes a secret and breaks it into t shares, n of which are needed in order to reconstruct the shares.
## Usage
No jar yet, so for the meantime:
javac ShamirSecretSharing.java
java ShamirSecretSharing share <path to file containing secret> <number of shares> <threshold>
java ShamirSecretSharing combine <path to file containing shares> <prime>
