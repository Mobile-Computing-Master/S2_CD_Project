package fh.thesis.thesisone.logic;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component("ProofOfWork")
public class ProofOfWork {

    public String calculateHash(String originalString, int difficulty) {
        boolean hashFound = false;
        int counter = 0;
        String hash = "";

        while (!hashFound) {
            hash = Hashing.sha256().hashString(originalString + counter++, StandardCharsets.UTF_8).toString();
            hashFound = hashHasDifficulty(hash, difficulty);
        }

        return hash;
    }

    private boolean hashHasDifficulty(String hash, int difficulty) {
        boolean hasDifficulty = true;
        for(int i = 0; i < difficulty; i++) {
            if (hash.charAt(i) != '0') {
                hasDifficulty = false;
                break;
            }
        }
        return hasDifficulty;
    }
}
