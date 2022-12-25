import java.lang.Integer.min
import java.util.Stack
import java.util.concurrent.atomic.AtomicInteger

/**
 * A vertex in a graph.
 * @param <T>  the type of the payload.
</T> */
internal interface Node<T> {
    /**
     * A list of nodes adjacent to this node. The link is directional from this node to the adjacent node.
     *
     * @return list of adjacent nodes
     */
    fun adjacents(): List<Node<T>?>?

    /**
     * The payload object stored in the node. Can for example be used to identify or number the node.
     *
     * @return the payload object.
     */
    val payload: T
}


internal object Task {
    /**
     * Calculates the strongly connected components of a directed acyclic graph using Tarjan's algorithm
     *
     * [Reference](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
     *
     * @param graph a directed acyclic graph
     * @return a list of strongly connected components of the specified graph
     */
    fun tarjan(graph: List<Node<*>?>?): List<List<Node<*>>>? {
        if (graph.isNullOrEmpty())
            return null
        val nodeTarjanData = HashMap<Node<*>, TarjanData>()

        val current = AtomicInteger(0)
        val stack = Stack<Node<*>?>()

        val result = ArrayList<List<Node<*>>>()

        graph
            .forEach { v ->
                if (v != null && !nodeTarjanData.containsKey(v))
                    strongconnect(nodeTarjanData, result, current, stack, v)
            }
        return result
    }

    private fun strongconnect(
        nodeTarjanData: HashMap<Node<*>, TarjanData>,
        result: ArrayList<List<Node<*>>>,
        current: AtomicInteger,
        stack: Stack<Node<*>?>,
        v: Node<*>?
    ) {
        // TODO: Input validation
        if (v == null) {
            return
        }
        stack.push(v)
        val currentTarjanData = TarjanData(current.get(), current.getAndIncrement(), true)
        nodeTarjanData[v] = currentTarjanData

        v.adjacents()?.forEach { adj ->
            if (!nodeTarjanData.containsKey(adj)) {
                strongconnect(nodeTarjanData, result, current, stack, adj)
                nodeTarjanData[adj]!!.lowlink = min(nodeTarjanData[adj]!!.lowlink, nodeTarjanData[v]!!.lowlink)

            } else if (nodeTarjanData[adj]!!.onStack) {
                nodeTarjanData[v]!!.lowlink = min(nodeTarjanData[adj]!!.lowlink, nodeTarjanData[v]!!.index)
            }
        }

        if (nodeTarjanData[v]!!.index == nodeTarjanData[v]!!.lowlink) {
            val scc = ArrayList<Node<*>>()
            do {
                val w = stack.pop()
                if (w != null) {
                    nodeTarjanData[w]!!.onStack = false
                    scc.add(w)
                }
            } while (v != w)
            result.add(scc)
        }
    }
}