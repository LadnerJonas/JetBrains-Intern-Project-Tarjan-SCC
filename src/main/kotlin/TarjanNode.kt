internal class TarjanNode<T>(override val payload: T) : Node<T> {
    private val adjacents = HashSet<Node<T>>()
    override fun adjacents(): List<Node<T>?>? {
        return adjacents.toList()
    }

    fun addAdjacentNode(w: Node<T>) {
        adjacents.add(w)
    }

    fun removeAdjacentNode(w: Node<T>) {
        adjacents.remove(w)
    }

}