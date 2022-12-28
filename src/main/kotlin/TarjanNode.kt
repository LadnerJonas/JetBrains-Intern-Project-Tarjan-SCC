internal class TarjanNode<T>(override val payload: T) : Node<T> {
    private val adjacents = HashSet<Node<T>>()
    override fun adjacents(): List<Node<T>?> {
        return adjacents.toList()
    }

    fun addAdjacentNode(w: Node<T>): TarjanNode<T> {
        adjacents.add(w)
        return this
    }

    fun removeAdjacentNode(w: Node<T>): TarjanNode<T> {
        adjacents.remove(w)
        return this
    }

}