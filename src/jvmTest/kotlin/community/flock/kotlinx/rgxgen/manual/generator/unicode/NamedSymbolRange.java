package community.flock.kotlinx.rgxgen.manual.generator.unicode;

import community.flock.kotlinx.rgxgen.model.SymbolRange;

public class NamedSymbolRange {
    public final SymbolRange range;
    public final RangeName   name;

    public NamedSymbolRange(SymbolRange range, RangeName name) {
        this.range = range;
        this.name = name;
    }
}
