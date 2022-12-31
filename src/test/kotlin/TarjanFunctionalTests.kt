import helper.TarjanTestHelper.validateTarjanResults
import node.Node
import node.TarjanNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Tests of the functionality of the tarjan algorithm implementation
 */

class TarjanFunctionalTests {
    /**
     * A test, which adds pseudo-random edges to graph and compares the tarjan results after each added-edge,
     * with the results of the [kosaraju algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
     */
    @Test
    fun pseudoRandomGraph() {
        val graph = ArrayList<Node<Int>>()
        val size = 512
        (1..size).forEach { graph.add(TarjanNode(it)) }

        val rnd = Random("Jonas Ladner".hashCode().toLong())

        val randomEdges = 512
        (1..randomEdges).forEach { _ ->

            (graph[rnd.nextInt(0, size)] as TarjanNode<Int>).addAdjacentNode(graph[rnd.nextInt(0, size)])

            validateTarjanResults(graph)
        }
    }

    /**
     * Tests the implementation against the [wikipedia example](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
     */
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

    /**
     * Tests the implementation against a small graph with two nodes
     */
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

    /**
     * Tests the implementation against a graph with a big strongly connected component
     */
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

    /**
     * Tests the implementation against a graph with two, big strongly connected components
     */
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

    /**
     * A statically asserted test, because changes in the dynamic testing framework could introduce bugs
     */
    @Test
    fun staticMediumGraph() {
        val graph = ArrayList<Node<Int>>()
        val size = 50
        (1..size).forEach { graph.add(TarjanNode(it)) }


        (1..size).forEach {
            (graph[it - 1] as TarjanNode<Int>).addAdjacentNode(graph[it % size])
        }

        // static tests
        assertEquals(1, Task.tarjan(graph)!!.size)
        val scc = Task.tarjan(graph)!![0]
        assertEquals(size, HashSet<Node<*>>(scc).size)

        // dynamic tests
        validateTarjanResults(graph)

    }

}