package helper

/**
 * The data needed per node to run the tarjan algorithm
 */
class TarjanNodeData(
    /**
     * The unique id, in which the node is discovered
     */
    var index: Int,
    /**
     * The smallest index on the stack, which is currently reachable through this node`s DFS
     */
    var lowlink: Int,
    /**
     * Keeps track if this node is currently on the tarjan stack
     */
    var onStack: Boolean
)