import node.TarjanNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests of the behavior of the tarjan algorithm implementation in edge cases
 */

class TarjanEdgeCaseTests {
    /**
     * Tests the behavior of the tarjan implementation against null, empty and small graphs
     */
    @Test
    fun noExceptionInEdgeCases() {
        assertDoesNotThrow {
            Task.tarjan(null)
            Task.tarjan(ArrayList())
            Task.tarjan(ArrayList(listOf(null, null)))
            Task.tarjan(ArrayList(listOf(TarjanNode(1), null)))
            Task.tarjan(ArrayList(listOf(null, TarjanNode(1))))
            val graph = ArrayList(listOf(TarjanNode(1)))
            graph[0].addAdjacentNode(graph[0])
            Task.tarjan(graph)
        }

    }
}