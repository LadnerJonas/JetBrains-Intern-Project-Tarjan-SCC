import org.junit.jupiter.api.Test

class TarjanTests {
    @Test
    fun simpleGraphs() {
        val g = ArrayList<Node<Int>>()
        val v0 = TarjanNode(0)
        val v1 = TarjanNode(1)
        v0.addAdjacentNode(v1)
        g.addAll(listOf(v0, v1))

        Task.tarjan(g)?.forEachIndexed { i, scc ->
            print("$i:")
            scc.forEach { v -> print(" ${v.payload}") }
            print("\n")
        }
        v1.addAdjacentNode(v0)
        Task.tarjan(g)?.forEachIndexed { i, scc ->
            print("$i:")
            scc.forEach { v -> print(" ${v.payload}") }
            print("\n")
        }
    }
}