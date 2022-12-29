package node

/**
 * A vertex, loaded with a [payload] of type [T] and the [adjacents], in a graph.
</T> */
internal interface Node<T> {
    /**
     * A list of nodes adjacent to this node. The link is directional from this node to the adjacent node.
     */
    fun adjacents(): List<Node<T>?>?

    /**
     * The payload object stored in the node. Can for example be used to identify or number the node.
     */
    val payload: T
}