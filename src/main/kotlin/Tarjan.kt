import helper.TarjanNodeData
import node.Node
import java.lang.Integer.min
import java.util.Stack
import java.util.concurrent.atomic.AtomicInteger

internal object Task {
    /**
     * Calculates the strongly connected components of a directed acyclic [graph] using Tarjan's algorithm
     *
     * [Reference](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
     */
    fun tarjan(graph: List<Node<*>?>?): List<List<Node<*>>>? {
        if (graph.isNullOrEmpty())
            return null

        val nodeData = HashMap<Node<*>, TarjanNodeData>()
        // make the index be copied by reference instead of value
        val currentIndex = AtomicInteger(0)
        val stack = Stack<Node<*>?>()
        val result = ArrayList<List<Node<*>>>()

        graph.forEach { v ->
            if (v != null && !nodeData.containsKey(v))
                stronglyConnectedComponent(result, nodeData, currentIndex, stack, v)
        }

        return result
    }

    /**
     * Generates the strongly connected component of a subgraph given by the [startingVertex]
     * [currentIndex] indexes the [startingVertex], which data is saved in the [nodeData]
     * [stack] contains all the nodes currently in the SCC
     * Adds the current SCC to the given [sccList]
     */

    private fun stronglyConnectedComponent(
        sccList: ArrayList<List<Node<*>>>,
        nodeData: HashMap<Node<*>, TarjanNodeData>,
        currentIndex: AtomicInteger,
        stack: Stack<Node<*>?>,
        startingVertex: Node<*>?
    ) {
        if (startingVertex == null) {
            return
        }

        stack.push(startingVertex)
        nodeData[startingVertex] = TarjanNodeData(currentIndex.get(), currentIndex.getAndIncrement(), true)

        startingVertex.adjacents()?.forEach { adj ->
            if (!nodeData.containsKey(adj)) {
                // visit the adjacent node
                stronglyConnectedComponent(sccList, nodeData, currentIndex, stack, adj)
                nodeData[adj]!!.lowlink = min(nodeData[adj]!!.lowlink, nodeData[startingVertex]!!.lowlink)

            } else if (nodeData[adj]!!.onStack) {
                // update the lowest reachable node index, as the adjacent node is in the same SCC
                nodeData[startingVertex]!!.lowlink = min(nodeData[adj]!!.lowlink, nodeData[startingVertex]!!.index)
            }
        }

        if (nodeData[startingVertex]!!.index == nodeData[startingVertex]!!.lowlink) {
            // form the strong connected component
            val scc = ArrayList<Node<*>>()
            do {
                val w = stack.pop()
                if (w != null) {
                    nodeData[w]!!.onStack = false
                    scc.add(w)
                }
            } while (startingVertex != w)
            sccList.add(scc)
        }
    }
}