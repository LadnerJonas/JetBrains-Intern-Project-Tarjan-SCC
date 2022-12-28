import TarjanTestHelper.validateTarjanResults
import org.junit.jupiter.api.Test

class TarjanTests {
    @Test
    fun simpleGraphs() {
        val g = ArrayList<Node<Int>>()
        val v0 = TarjanNode(0)
        val v1 = TarjanNode(1)
        v0.addAdjacentNode(v1)
        g.addAll(listOf(v0, v1))

        validateTarjanResults(g)

        v1.addAdjacentNode(v0)

        validateTarjanResults(g)

    }

    @Test
    fun wikipediaExample() {
        val g = ArrayList<Node<Int>>()
        (1..8).forEach { g.add(TarjanNode(it)) }
        (1..3).forEach { (g[it - 1] as TarjanNode<Int>).addAdjacentNode(g[it % 3]).addAdjacentNode(g[(it + 1) % 3]) }

        (g[3] as TarjanNode<Int>).addAdjacentNode(g[4])
        (g[4] as TarjanNode<Int>).addAdjacentNode(g[3])

        (g[5] as TarjanNode<Int>).addAdjacentNode(g[6])
        (g[6] as TarjanNode<Int>).addAdjacentNode(g[5])

        (g[7] as TarjanNode<Int>).addAdjacentNode(g[7])

        validateTarjanResults(g)
    }
}