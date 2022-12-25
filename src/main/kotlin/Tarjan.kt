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
        // TODO implement me
        return null
    }
}