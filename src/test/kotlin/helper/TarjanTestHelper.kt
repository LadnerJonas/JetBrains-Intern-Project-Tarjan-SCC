package helper

import Task
import node.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * A collection of functions to test the tarjan implementation
 */
internal object TarjanTestHelper {
    /**
     * Returns the strongly connected component with the greatest cardinality of a given [nodeList]
     */
    private fun computeGreatestStronglyConnectedComponent(nodeList: List<Node<*>?>?): Set<Node<*>> {
        if (nodeList.isNullOrEmpty()) {
            return HashSet()
        }

        print("given nodeList: ")
        nodeList.filterNotNull().forEach { print("${it.payload} ") }
        print('\n')

        val result = HashSet<Node<*>>()
        if (nodeList[0]?.adjacents() != null) {
            result.add(nodeList[0]!!)
            for (e in nodeList[0]?.adjacents()!!) {
                if (e != null) {
                    result.add(e)
                }
            }

            for (v in nodeList.toList()) {
                val current = HashSet<Node<*>>()
                if (v?.adjacents() != null) {
                    current.add(v)
                    for (e in v.adjacents()!!) {
                        if (e != null) {
                            current.add(e)
                        }
                    }
                }
                // intersection of the sets of adjacent nodes
                result.retainAll(current)
            }
        }


        // Check if are additional nodes inside the scc
        val initialNodes = result.toList()
        for (v in initialNodes) {
            if (nodeList.indexOf(v) == -1) {
                val current = HashSet<Node<*>>()
                if (v.adjacents() != null) {
                    for (e in v.adjacents()!!) {
                        if (e != null) {
                            current.add(e)
                        }
                    }
                }
                result.retainAll(current)
            }
        }

        // if no scc of size >1 can be created, pick a single node and create a scc
        if (result.isEmpty()) {
            nodeList[0]?.let { result.add(it) }
        }

        print("scc: ")
        result.toList().forEach { print("${it.payload} ") }
        print('\n')

        return result
    }

    /**
     * Asserts the equality of a given [noteList] and the given [actualSCC]
     */

    private fun validateStronglyConnectedComponents(noteList: List<Node<*>?>?, actualSCC: Set<Node<*>>) {
        val givenNodeSet = HashSet<Node<*>>()
        if (noteList != null) {
            for (v in noteList.toList()) {
                if (v != null) {
                    givenNodeSet.add(v)
                }
            }
        }
        assertEquals(givenNodeSet.size, actualSCC.size)
        givenNodeSet.retainAll(actualSCC)
        assertEquals(givenNodeSet.size, actualSCC.size)
    }

    /**
     * Asserts the validity of the computed strongly connected graphs of a given [graph]
     */
    fun validateTarjanResults(graph: List<Node<*>?>?) {
        if (graph.isNullOrEmpty())
            return

        graph.filterNotNull().forEach { print("${it.payload} ") }
        print('\n')

        val tarjanResult = Task.tarjan(graph)

        Assertions.assertFalse(tarjanResult == null)

        val countOfNodes = tarjanResult!!.sumOf { it.count() }

        assertEquals(countOfNodes, graph.filterNotNull().count())

        tarjanResult.forEach { givenSCC ->
            validateStronglyConnectedComponents(
                givenSCC,
                computeGreatestStronglyConnectedComponent(givenSCC)
            )
        }
    }

}