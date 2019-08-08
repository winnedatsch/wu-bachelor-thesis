package at.hadl.logstatistics.utils;

import at.hadl.logstatistics.utils.graphbuilding.LabeledEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.List;
import java.util.stream.Collectors;

public class RequiredPartitionsExtractor {
    public static String extractStarShapes(List<DefaultDirectedGraph<String, LabeledEdge>> queryGraphs) {
        return queryGraphs.stream()
                .flatMap(queryGraph -> queryGraph.vertexSet().stream()
                        .filter(vertex -> !queryGraph.outgoingEdgesOf(vertex).isEmpty())
                        .map(vertex -> queryGraph.outgoingEdgesOf(vertex).stream()
                                .map(LabeledEdge::getPredicate)
                                .distinct()
                                .sorted()
                                .map(Object::toString)
                                .collect(Collectors.joining(",", "\"", "\""))))
                .distinct()
                .collect(Collectors.joining(",", "[", "]"));
    }
}
