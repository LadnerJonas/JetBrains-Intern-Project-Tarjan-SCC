package helper

import node.Node
import java.util.*
import kotlin.collections.ArrayList

/**
 * A wrapper class for the [kosaraju algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm) implementation
 */
internal object Kosaraju {
    /**
     * An implementation of the [kosaraju algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
     * with computes the strongly connected graphs within a graph
     */
    fun kosaraju(graph: List<Node<*>?>): ArrayList<List<Node<*>>> {
        val filteredGraph = graph.filterNotNull()
        val indexMap = HashMap<Node<*>, Int>(graph.size)

        // create adj list for easier graph reversal
        val adj = Array<List<Node<*>>>(graph.size) { ArrayList() }
        filteredGraph.forEachIndexed { index, node ->
            indexMap[node] = index
            if (node.adjacents() != null)
                adj[index] = node.adjacents()!!.filterNotNull()
        }

        val stack = Stack<Node<*>>()
        var visited = BooleanArray(filteredGraph.size) { false }

        // first dfs walkthrough
        filteredGraph.forEachIndexed { index, node ->
            if (!visited[index]) dfs(node, visited, indexMap, stack)
        }

        visited = BooleanArray(filteredGraph.size) { false }

        // reverse graph
        val adjRev = Array<ArrayList<Node<*>>>(graph.size) { ArrayList() }
        adj.forEachIndexed { index, node ->
            node.forEach { adj ->
                adjRev[indexMap[adj]!!].add(filteredGraph[index])
            }
        }

        // second dfs walkthrough
        val result = ArrayList<List<Node<*>>>()
        while (!stack.isEmpty()) {
            val currentNode = stack.pop()
            if (indexMap.containsKey(currentNode) && indexMap[currentNode] != null && !visited[indexMap[currentNode]!!]) {
                val scc = ArrayList<Node<*>>()

                dfs_rev(currentNode, adjRev, visited, indexMap, scc)

                result.add(scc)
            }
        }
        return result
    }

    /**
     * Builds up a post-order stack of all the nodes by using a dfs
     */
    private fun dfs(
        currentNode: Node<*>,
        visited: BooleanArray,
        indexMap: HashMap<Node<*>, Int>,
        stack: Stack<Node<*>>
    ) {
        visited[indexMap[currentNode]!!] = true
        currentNode.adjacents()?.forEach {
            if (it != null && indexMap.containsKey(it) && !visited[indexMap[it]!!]) {
                dfs(it, visited, indexMap, stack)
            }
        }
        stack.push(currentNode)
    }

    /**
     * Builds up all the strongly connected components by using a dfs
     */
    private fun dfs_rev(
        currentNode: Node<*>,
        adjRev: Array<ArrayList<Node<*>>>,
        visited: BooleanArray,
        indexMap: HashMap<Node<*>, Int>,
        scc: ArrayList<Node<*>>
    ) {
        visited[indexMap[currentNode]!!] = true
        scc.add(currentNode)
        adjRev[indexMap[currentNode]!!].forEach {
            if (indexMap.containsKey(it) && !visited[indexMap[it]!!]) {
                dfs_rev(it, adjRev, visited, indexMap, scc)
            }
        }
    }


}