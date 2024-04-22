package community.flock.kotlinx.rgxgen.testutil

import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Sequence
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.visitors.NodeVisitor

class NodePatternCollectingVisitor : NodeVisitor {
    private val aPatterns: MutableList<String> = ArrayList()

    override fun visit(node: SymbolSet) {
        aPatterns.add(node.pattern)
    }

    override fun visit(node: Choice) {
        aPatterns.add(node.pattern)
        for (nodeNode in node.nodes) {
            nodeNode.visit(this)
        }
    }

    override fun visit(node: FinalSymbol) {
        aPatterns.add(node.pattern)
    }

    override fun visit(node: Repeat) {
        aPatterns.add(node.pattern)
        node.node
            .visit(this)
    }

    override fun visit(node: Sequence) {
        aPatterns.add(node.pattern)
        for (nodeNode in node.nodes) {
            nodeNode.visit(this)
        }
    }

    override fun visit(node: NotSymbol) {
        aPatterns.add(node.pattern)
    }

    override fun visit(node: GroupRef) {
        aPatterns.add(node.pattern)
    }

    override fun visit(node: Group) {
        aPatterns.add(node.pattern)
        node.node
            .visit(this)
    }

    val patterns: List<String>
        get() = aPatterns
}
