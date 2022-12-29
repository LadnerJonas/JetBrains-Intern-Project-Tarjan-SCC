package node

/**
 * Implementation of [Node] to be able to test the tarjan algorithm implementation
 */

internal class TarjanNode<T>(override val payload: T) : Node<T> {
    private val adjacents = HashSet<Node<T>>()
    override fun adjacents(): List<Node<T>?> {
        return adjacents.toList()
    }

    /**
     * Adds a [adjacentNode] to the [adjacents]
     */
    fun addAdjacentNode(adjacentNode: Node<T>): TarjanNode<T> {
        adjacents.add(adjacentNode)
        return this
    }

    /**
     * Removes a [adjacentNode] from the [adjacents]
     */
    fun removeAdjacentNode(adjacentNode: Node<T>): TarjanNode<T> {
        adjacents.remove(adjacentNode)
        return this
    }

}