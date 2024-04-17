package community.flock.kotlinx.rgxgen.data;

import community.flock.kotlinx.rgxgen.nodes.Node;

import java.math.BigInteger;
import java.util.List;

public interface DataInterface {

    boolean hasEstimatedCount();

    boolean hasAllUniqueValues();

    boolean useFindForMatching();

    boolean isUsableWithJavaPattern();

    String getPattern();

    Node getResultNode();

    BigInteger getEstimatedCount();

    List<String> getAllUniqueValues();
}
