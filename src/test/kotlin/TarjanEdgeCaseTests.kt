import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class TarjanEdgeCaseTests {
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