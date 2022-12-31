<div style="text-align: center;"> 
    <h1>JetBrains-Intern-Project-Tarjan-SCC</h1>
    <h4 style="text-align: center;">Code-Quality: <a href="https://www.codefactor.io/repository/github/ladnerjonas/jetbrains-intern-project-tarjan-scc/badge?s=81648a0e18c9125b926457682b6e56cec5a1519e">
        <img alt="Codefactor.io Badge" src="https://www.codefactor.io/repository/github/ladnerjonas/jetbrains-intern-project-tarjan-scc/badge?s=81648a0e18c9125b926457682b6e56cec5a1519e" />
    </a></h4>
</div>

As part of my application as an intern, I was asked to implement
[Tarjan's algorithm](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm) for
finding strongly connected components.

# Documentation

Just a brief overview to explain the basic structure of the project

### Module [src.main.kotlin](src/main/kotlin)

You can find the Tarjan implementation inside the [Tarjan.kt](src/main/kotlin/Tarjan.kt) file.

The [Node.kt](src/main/kotlin/node/Node.kt) interface and the sample implementation
[TarjanNode.kt](src/main/kotlin/node/TarjanNode.kt) can be found inside the [node](src/main/kotlin/node) subdirectory.

Helper classes, like the [TarjanNodeData.kt](src/main/kotlin/helper/TarjanNodeData.kt),
can be found inside the [helper](src/main/kotlin/helper) subdirectory. This class packs all the data needed per node
inside
a single class, which greatly helps the readability and cache-efficiency of the Tarjan implementation.

### Module [src.test.kotlin](src/test/kotlin)

All the tests are divided into two groups: [TarjanFunctionalTests.kt](src/test/kotlin/TarjanFunctionalTests.kt) and
[TarjanEdgeCaseTests.kt](src/test/kotlin/TarjanEdgeCaseTests.kt).

To further ease the testing of huge graphs, a dynamic testing framework was created, which can found inside the
[helper](src/test/kotlin/helper) subdirectory. For example, the
[TarjanTestHelper.kt](src/test/kotlin/helper/TarjanTestHelper.kt) file verifies the output of the Tarjan
implementation, by checking the count and content of the strongly connected components (SCC) compared to the 
[Kosaraju.kt](src/test/kotlin/helper/Kosaraju.kt) algorithm. 
