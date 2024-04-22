package community.flock.kotlinx.rgxgen.testutil

import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Sequence
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.visitors.NodeVisitor

class NodePatternVerifyingVisitor(node: Node) : NodeVisitor {
    private val aPatterns: List<String?>?
    private val aErrors: MutableList<String>

    private var aCurrentIndex = -1

    init {
        val visitor = NodePatternCollectingVisitor()
        node.visit(visitor)
        aErrors = ArrayList()
        aPatterns = visitor.patterns
    }

    override fun visit(node: SymbolSet) {
        compareAndReport(node)
    }

    override fun visit(node: Choice) {
        compareAndReport(node)
    }

    override fun visit(node: FinalSymbol) {
        compareAndReport(node)
    }

    override fun visit(node: Repeat) {
        compareAndReport(node)
    }

    override fun visit(node: Sequence) {
        compareAndReport(node)
    }

    override fun visit(node: NotSymbol) {
        compareAndReport(node)
    }

    override fun visit(node: GroupRef) {
        compareAndReport(node)
    }

    override fun visit(node: Group) {
        compareAndReport(node)
    }

    private fun compareAndReport(node: Node) {
        ++aCurrentIndex
        if (node.pattern != aPatterns!![aCurrentIndex]) {
            aErrors.add('\''.toString() + node.pattern + "' not equal to '" + aPatterns[aCurrentIndex] + '\'')
        }
    }

    val errors: Collection<String>
        get() = aErrors
}
