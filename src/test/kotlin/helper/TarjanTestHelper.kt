package helper

import Task
import node.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * A collection of functions to test the tarjan implementation
 */
internal object TarjanTestHelper {

    /**
     * Asserts the validity of the computed strongly connected components of a given [graph]
     * through a comparison of the tarjan results with those of the [kosaraju algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
     */
    fun validateTarjanResults(graph: List<Node<*>?>?) {
        if (graph.isNullOrEmpty())
            return

        val tarjanResult = Task.tarjan(graph)

        Assertions.assertFalse(tarjanResult == null)
        val countOfNodes = tarjanResult!!.sumOf { it.count() }
        assertEquals(graph.filterNotNull().count(), countOfNodes)

        val kosarajuResult = Kosaraju.kosaraju(graph)

        assertEquals(kosarajuResult.size, tarjanResult.size)
        kosarajuResult.forEach { scc ->
            val possibleTarjanLists = tarjanResult.filter { scc.size == it.size }
            assertTrue(possibleTarjanLists.isNotEmpty())

            var lastIndex = 0
            scc.forEachIndexed { index, node ->
                var currentIndex = -1
                assertDoesNotThrow {
                    currentIndex =
                        possibleTarjanLists.indexOf(possibleTarjanLists.first { list -> list.contains(node) })
                }
                // check if all nodes are in the same strongly connected components
                if (index > 0)
                    assertTrue(currentIndex == lastIndex)
                lastIndex = currentIndex

            }
        }
    }
}