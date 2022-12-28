import helper.TarjanTestHelper.validateTarjanResults
import org.junit.jupiter.api.Test

class TarjanFunctionalTests {
    @Test
    fun simpleGraphs() {
        val graph = ArrayList<Node<Int>>()
        val v0 = TarjanNode(0)
        val v1 = TarjanNode(1)
        v0.addAdjacentNode(v1)
        graph.addAll(listOf(v0, v1))

        validateTarjanResults(graph)

        v1.addAdjacentNode(v0)

        validateTarjanResults(graph)

    }

    @Test
    fun wikipediaExample() {
        val graph = ArrayList<Node<Int>>()
        (1..8).forEach { graph.add(TarjanNode(it)) }
        (1..3).forEach {
            (graph[it - 1] as TarjanNode<Int>).addAdjacentNode(graph[it % 3]).addAdjacentNode(graph[(it + 1) % 3])
        }

        (graph[3] as TarjanNode<Int>).addAdjacentNode(graph[4])
        (graph[4] as TarjanNode<Int>).addAdjacentNode(graph[3])

        (graph[5] as TarjanNode<Int>).addAdjacentNode(graph[6])
        (graph[6] as TarjanNode<Int>).addAdjacentNode(graph[5])

        (graph[7] as TarjanNode<Int>).addAdjacentNode(graph[7])

        validateTarjanResults(graph)
    }

    @Test
    fun bigGraph() {
        val graph = ArrayList<Node<Int>>()
        val size = 1023
        (0..size).forEach { graph.add(TarjanNode(it)) }
        (0..size).forEach { it1 ->
            (0..size).forEach { it2 ->
                (graph[it1] as TarjanNode).addAdjacentNode(graph[it2])
            }
        }

        validateTarjanResults(graph)
    }

    @Test
    fun bigGraphWithTwoSCC() {
        val graph = ArrayList<Node<Int>>()
        val size = 1023
        (0..size).forEach { graph.add(TarjanNode(it)) }
        (0..size step 2).forEach { it1 ->
            (0..size step 2).forEach { it2 ->
                (graph[it1] as TarjanNode).addAdjacentNode(graph[it2])
            }
        }
        (1..size step 2).forEach { it1 ->
            (1..size step 2).forEach { it2 ->
                (graph[it1] as TarjanNode).addAdjacentNode(graph[it2])
            }
        }

        validateTarjanResults(graph)
    }
}